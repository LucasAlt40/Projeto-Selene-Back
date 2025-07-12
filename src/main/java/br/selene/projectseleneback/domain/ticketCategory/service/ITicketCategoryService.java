package br.selene.projectseleneback.domain.ticketCategory.service;

import org.springframework.data.domain.Page;

import br.selene.projectseleneback.domain.ticketCategory.TicketCategory;
import br.selene.projectseleneback.domain.ticketCategory.dto.CreateTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.SearchTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.UpdateTicketCategoryDTO;

public interface ITicketCategoryService {
	Page<TicketCategory> findAll(SearchTicketCategoryDTO searchTicketCategoryDTO);
	TicketCategory create(CreateTicketCategoryDTO createTicketCategoryDTO);
	TicketCategory update(Long TicketCategoryId, UpdateTicketCategoryDTO updateTicketCategoryDTO);
	Boolean reserveTicket(Long ticketCategoryId, int quantity);
	Boolean releaseTicket(Long ticketCategoryId, int quantity);
}
