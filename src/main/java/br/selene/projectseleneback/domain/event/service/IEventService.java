package br.selene.projectseleneback.domain.event.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import br.selene.projectseleneback.domain.event.dto.CreateEventDTO;
import br.selene.projectseleneback.domain.event.dto.CreateTicketCategoryDTO;
import br.selene.projectseleneback.domain.event.dto.EventDTO;
import br.selene.projectseleneback.domain.event.dto.SearchEventDTO;
import br.selene.projectseleneback.domain.event.dto.TicketCategoryDTO;
import br.selene.projectseleneback.domain.event.dto.UpdateEventDTO;
import br.selene.projectseleneback.domain.event.dto.UpdateTicketCategoryDTO;
import br.selene.projectseleneback.infra.exception.TicketOperationException;

public interface IEventService {
	
	Page<EventDTO> findAll(SearchEventDTO searchEventDTO);
	
	EventDTO findById(Long id);
	
	EventDTO createEvent(CreateEventDTO createEventDTO, MultipartFile file) throws IOException;
	
	EventDTO updateEvent(Long eventId, UpdateEventDTO updateEventDTO, MultipartFile file) throws IOException;
	
	List<TicketCategoryDTO> findTicketCategoriesFromEvent(Long eventId);

	TicketCategoryDTO findTicketCategoryById(Long ticketCategoryId);

	TicketCategoryDTO addTicketCategory(Long eventId, CreateTicketCategoryDTO createTicketCategoryDTO);

	TicketCategoryDTO updateTicketCategory(Long eventId, Long ticketCategoryId, UpdateTicketCategoryDTO updateTicketCategoryDTO);

	void reserveTicket(Long ticketCategoryId, int quantity) throws TicketOperationException;

	void releaseTicket(Long ticketCategoryId, int quantity) throws TicketOperationException;
	
}
