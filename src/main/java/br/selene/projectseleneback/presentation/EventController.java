package br.selene.projectseleneback.presentation;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.selene.projectseleneback.domain.event.dto.CreateEventDTO;
import br.selene.projectseleneback.domain.event.dto.CreateEventForm;
import br.selene.projectseleneback.domain.event.dto.CreateTicketCategoryDTO;
import br.selene.projectseleneback.domain.event.dto.EventDTO;
import br.selene.projectseleneback.domain.event.dto.SearchEventDTO;
import br.selene.projectseleneback.domain.event.dto.TicketCategoryDTO;
import br.selene.projectseleneback.domain.event.dto.UpdateEventDTO;
import br.selene.projectseleneback.domain.event.dto.UpdateEventForm;
import br.selene.projectseleneback.domain.event.dto.UpdateTicketCategoryDTO;
import br.selene.projectseleneback.domain.event.service.IEventService;
import br.selene.projectseleneback.presentation.utils.CustomPage;
import jakarta.validation.Valid;

@RestController
@RequestMapping(MappingEndpoint.Event.MAIN)
public class EventController {

	private final IEventService eventService;

	public EventController(IEventService eventService) {
		this.eventService = eventService;
	}

	@GetMapping(MappingEndpoint.FIND)
	@ResponseStatus(HttpStatus.OK)
	public CustomPage<EventDTO> findAll(SearchEventDTO searchEventDTO) {
		return new CustomPage<>(eventService.findAll(searchEventDTO));
	}

	@PostMapping(value = MappingEndpoint.CREATE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public EventDTO createEvent(@ModelAttribute @Valid CreateEventForm form) throws IOException {
		CreateEventDTO createEventDTO = form.getEvent();
	    MultipartFile file = form.getFile();
		return eventService.createEvent(createEventDTO, file);
	}

	@PutMapping(value = "/{id}" + MappingEndpoint.UPDATE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public EventDTO updateEvent(
	        @PathVariable Long id,
	        @ModelAttribute @Valid UpdateEventForm form
	) throws IOException {
		UpdateEventDTO updateEventDTO = form.getEvent();
	    MultipartFile file = form.getFile();
	    return eventService.updateEvent(id, updateEventDTO, file);
	}

	@GetMapping("/{id}" + MappingEndpoint.Event.TICKET_CATEGORY_FIND)
	@ResponseStatus(HttpStatus.OK)
	public List<TicketCategoryDTO> findTicketCategories(@PathVariable Long id) {
		return eventService.findTicketCategoriesFromEvent(id);
	}

	@PostMapping("/{id}" + MappingEndpoint.Event.TICKET_CATEGORY_ADD)
	@ResponseStatus(HttpStatus.CREATED)
	public TicketCategoryDTO addTicketCategory(@PathVariable Long id,
			@RequestBody @Valid CreateTicketCategoryDTO createTicketCategoryDTO) {
		return eventService.addTicketCategory(id, createTicketCategoryDTO);
	}

	@PutMapping("/{id}" + MappingEndpoint.Event.TICKET_CATEGORY + "/{ticketCategoryId}" + MappingEndpoint.UPDATE)
	@ResponseStatus(HttpStatus.OK)
	public TicketCategoryDTO updateTicketCategory(@PathVariable Long id, @PathVariable Long ticketCategoryId,
			@RequestBody @Valid UpdateTicketCategoryDTO updateTicketCategoryDTO) {
		return eventService.updateTicketCategory(id, ticketCategoryId, updateTicketCategoryDTO);
	}

}
