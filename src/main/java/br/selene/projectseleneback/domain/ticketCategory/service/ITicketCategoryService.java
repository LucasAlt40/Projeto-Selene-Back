package br.selene.projectseleneback.domain.ticketCategory.service;

import org.springframework.data.domain.Page;

import br.selene.projectseleneback.domain.ticketCategory.dto.CreateTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.SearchTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.TicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.UpdateTicketCategoryDTO;
import br.selene.projectseleneback.infra.exception.TicketOperationException;

public interface ITicketCategoryService {
	Page<TicketCategoryDTO> findAll(SearchTicketCategoryDTO searchTicketCategoryDTO);

	TicketCategoryDTO create(CreateTicketCategoryDTO createTicketCategoryDTO);

	TicketCategoryDTO update(Long TicketCategoryId, UpdateTicketCategoryDTO updateTicketCategoryDTO);

	TicketCategoryDTO findById(Long ticketCategoryId);

	void reserveTicket(Long ticketCategoryId, int quantity) throws TicketOperationException;

	void releaseTicket(Long ticketCategoryId, int quantity) throws TicketOperationException;
}
