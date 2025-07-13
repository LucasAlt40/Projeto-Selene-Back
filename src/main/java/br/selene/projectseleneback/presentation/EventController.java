package br.selene.projectseleneback.presentation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.selene.projectseleneback.domain.event.dto.CreateTicketCategoryDTO;
import br.selene.projectseleneback.domain.event.dto.TicketCategoryDTO;
import br.selene.projectseleneback.domain.event.dto.UpdateTicketCategoryDTO;
import br.selene.projectseleneback.domain.event.service.IEventService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(MappingEndpoint.Event.MAIN)
public class EventController {

	private final IEventService eventService;

	public EventController(IEventService eventService) {
		this.eventService = eventService;
	}

	@GetMapping("/{id}" + MappingEndpoint.Event.TICKET_CATEGORY_FIND)
	@ResponseStatus(HttpStatus.OK)
	public List<TicketCategoryDTO> find(@PathVariable Long id) {
		return eventService.findTicketCategoriesFromEvent(id);
	}

	@PostMapping("/{id}" + MappingEndpoint.Event.TICKET_CATEGORY_ADD)
	@ResponseStatus(HttpStatus.CREATED)
	public TicketCategoryDTO create(@PathVariable Long id,
			@RequestBody @Valid CreateTicketCategoryDTO createTicketCategoryDTO) {
		return eventService.addTicketCategory(id, createTicketCategoryDTO);
	}

	@PutMapping("/{id}" + MappingEndpoint.Event.TICKET_CATEGORY + "/{ticketCategoryId}" + MappingEndpoint.UPDATE)
	@ResponseStatus(HttpStatus.OK)
	public TicketCategoryDTO update(@PathVariable Long id, @PathVariable Long ticketCategoryId,
			@RequestBody @Valid UpdateTicketCategoryDTO updateTicketCategoryDTO) {
		return eventService.updateTicketCategory(id, ticketCategoryId, updateTicketCategoryDTO);
	}

}
