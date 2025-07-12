package br.selene.projectseleneback.domain.ticketCategory.repository;

import br.selene.projectseleneback.domain.ticketCategory.TicketCategory;
import br.selene.projectseleneback.domain.ticketCategory.dto.SearchTicketCategoryDTO;

import org.springframework.data.domain.Page;

public interface ITicketCategoryRepository {

	TicketCategory save(TicketCategory ticketCategory);
	Page<TicketCategory> findAll(SearchTicketCategoryDTO searchTicketCategoryDTO);
	TicketCategory findById(Long ticketCategoryId);
	Boolean reserveTicket(Long ticketCategoryId, int quantity);
	Boolean releaseTicket(Long ticketCategoryId, int quantity);

}
