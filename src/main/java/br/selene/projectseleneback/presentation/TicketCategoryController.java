package br.selene.projectseleneback.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.selene.projectseleneback.domain.ticketCategory.TicketCategory;
import br.selene.projectseleneback.domain.ticketCategory.dto.CreateTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.SearchTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.UpdateTicketCategoryDTO;
import br.selene.projectseleneback.infra.services.TicketCategoryService;
import br.selene.projectseleneback.presentation.utils.CustomPage;
import jakarta.validation.Valid;


@RestController
@RequestMapping(MappingEndpoint.TicketCategory.MAIN)
public class TicketCategoryController {

    private final TicketCategoryService ticketCategoryService;

    public TicketCategoryController(TicketCategoryService ticketCategoryService) { this.ticketCategoryService = ticketCategoryService;
    }

    @GetMapping(MappingEndpoint.FIND)
    @ResponseStatus(HttpStatus.OK)
    public CustomPage<TicketCategory> findAll(@Valid @ModelAttribute SearchTicketCategoryDTO searchTicketCategoryDTO){
        return new CustomPage<TicketCategory>(ticketCategoryService.findAll(searchTicketCategoryDTO));
    }

    @PostMapping(MappingEndpoint.CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    public TicketCategory create(@RequestBody CreateTicketCategoryDTO createTicketCategoryDTO) {
        return ticketCategoryService.create(createTicketCategoryDTO);
    }

    @PostMapping("/{id}" + MappingEndpoint.UPDATE)
    @ResponseStatus(HttpStatus.OK)
    public TicketCategory update(@PathVariable Long id, @RequestBody UpdateTicketCategoryDTO updateTicketCategoryDTO) {
        return ticketCategoryService.update(id, updateTicketCategoryDTO);
    }


}
