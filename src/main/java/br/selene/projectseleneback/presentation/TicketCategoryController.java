package br.selene.projectseleneback.presentation;

import br.selene.projectseleneback.domain.ticketCategory.TicketCategory;
import br.selene.projectseleneback.domain.ticketCategory.dto.CreateTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.UpdateTicketCategoryDTO;
import br.selene.projectseleneback.infra.services.TicketCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(MappingEndpoint.TicketCategory.MAIN)
public class TicketCategoryController {

    private final TicketCategoryService ticketCategoryService;

    public TicketCategoryController(TicketCategoryService ticketCategoryService) { this.ticketCategoryService = ticketCategoryService;
    }

    @GetMapping(MappingEndpoint.FIND)
    public Page<TicketCategory> findAll(){
        return ticketCategoryService.findAll(null);
    }

    @PostMapping(MappingEndpoint.CREATE)
    public TicketCategory create(@RequestBody CreateTicketCategoryDTO createTicketCategoryDTO) {
        return ticketCategoryService.create(createTicketCategoryDTO);
    }

    @PostMapping("/{id}" + MappingEndpoint.UPDATE)
    public TicketCategory update(@PathVariable Long id, @RequestBody UpdateTicketCategoryDTO updateTicketCategoryDTO) {
        return ticketCategoryService.update(id, updateTicketCategoryDTO);
    }


}
