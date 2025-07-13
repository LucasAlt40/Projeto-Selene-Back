package br.selene.projectseleneback.domain.event.service;

import java.util.List;

import br.selene.projectseleneback.domain.event.dto.CreateTicketCategoryDTO;
import br.selene.projectseleneback.domain.event.dto.TicketCategoryDTO;
import br.selene.projectseleneback.domain.event.dto.UpdateTicketCategoryDTO;
import br.selene.projectseleneback.infra.exception.TicketOperationException;

public interface IEventService {
	
	List<TicketCategoryDTO> findTicketCategoriesFromEvent(Long eventId);

	TicketCategoryDTO findTicketCategoryById(Long ticketCategoryId);

	TicketCategoryDTO addTicketCategory(Long eventId, CreateTicketCategoryDTO createTicketCategoryDTO);

	TicketCategoryDTO updateTicketCategory(Long eventId, Long ticketCategoryId, UpdateTicketCategoryDTO updateTicketCategoryDTO);

	void reserveTicket(Long ticketCategoryId, int quantity) throws TicketOperationException;

	void releaseTicket(Long ticketCategoryId, int quantity) throws TicketOperationException;
	
}
