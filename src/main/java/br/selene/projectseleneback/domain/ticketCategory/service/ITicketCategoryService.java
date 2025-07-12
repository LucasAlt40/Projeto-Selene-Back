package br.selene.projectseleneback.domain.ticketCategory.service;

import org.springframework.data.domain.Page;

import br.selene.projectseleneback.domain.ticketCategory.dto.CreateTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.SearchTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.TicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.UpdateTicketCategoryDTO;
import br.selene.projectseleneback.infra.exception.TicketOperationException;

public interface ITicketCategoryService {
	public Page<TicketCategoryDTO> findAll(SearchTicketCategoryDTO searchTicketCategoryDTO);

	public TicketCategoryDTO create(CreateTicketCategoryDTO createTicketCategoryDTO);

	public TicketCategoryDTO update(Long TicketCategoryId, UpdateTicketCategoryDTO updateTicketCategoryDTO);

	public void reserveTicket(Long ticketCategoryId, int quantity) throws TicketOperationException;

	public void releaseTicket(Long ticketCategoryId, int quantity) throws TicketOperationException;
}
