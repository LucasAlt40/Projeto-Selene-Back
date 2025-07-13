package br.selene.projectseleneback.domain.event.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;

import br.selene.projectseleneback.domain.event.Event;
import br.selene.projectseleneback.domain.event.TicketCategory;
import br.selene.projectseleneback.domain.event.dto.SearchEventDTO;

public interface IEventRepository {

	Page<Event> findAll(SearchEventDTO searchEventDTO);

	Event findById(Long eventId);
	
	Event save(Event event);

	TicketCategory addTicketCategory(TicketCategory ticketCategory);

	TicketCategory updateTicketCategory(TicketCategory ticketCategory);

	Collection<TicketCategory> findTicketCategoriesFromEvent(Long eventId);

	TicketCategory findTicketCategoryById(Long ticketCategoryId);

	Boolean reserveTicket(Long ticketCategoryId, int quantity);

	Boolean releaseTicket(Long ticketCategoryId, int quantity);

}
