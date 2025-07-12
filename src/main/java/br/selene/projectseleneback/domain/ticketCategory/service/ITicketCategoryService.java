package br.selene.projectseleneback.domain.ticketCategory.service;

import org.springframework.data.domain.Page;

import br.selene.projectseleneback.domain.ticketCategory.TicketCategory;
import br.selene.projectseleneback.domain.ticketCategory.dto.CreateTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.SearchTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.UpdateTicketCategoryDTO;

public interface ITicketCategoryService {

	public Page<TicketCategory> findAll(SearchTicketCategoryDTO searchTicketCategoryDTO);

	public TicketCategory create(CreateTicketCategoryDTO createTicketCategoryDTO);

	public TicketCategory update(Long TicketCategoryId, UpdateTicketCategoryDTO updateTicketCategoryDTO);
	
	public void reserveTicket(Long ticketCategoryId, int quantity);

}
