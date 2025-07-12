package br.selene.projectseleneback.infra.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.selene.projectseleneback.domain.ticketCategory.TicketCategory;
import br.selene.projectseleneback.domain.ticketCategory.dto.CreateTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.SearchTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.UpdateTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.repository.ITicketCategoryRepository;
import br.selene.projectseleneback.domain.ticketCategory.service.ITicketCategoryService;
import jakarta.validation.Valid;

@Service
public class TicketCategoryService implements ITicketCategoryService {

	private final ITicketCategoryRepository ticketCategoryRepository;

	public TicketCategoryService(ITicketCategoryRepository ticketCategoryRepository) {
		this.ticketCategoryRepository = ticketCategoryRepository;
	}

	public Page<TicketCategory> findAll(@Valid SearchTicketCategoryDTO searchTicketCategoryDTO) {
		return ticketCategoryRepository.findAll(searchTicketCategoryDTO);
	}

	public TicketCategory create(CreateTicketCategoryDTO createTicketCategoryDTO) {
		TicketCategory newTicketCategory = new TicketCategory();

		newTicketCategory.setPrice(createTicketCategoryDTO.price());
		newTicketCategory.setDescription(createTicketCategoryDTO.description());
		newTicketCategory.setQuantity(createTicketCategoryDTO.quantity());

		return ticketCategoryRepository.save(newTicketCategory);
	}

	public TicketCategory update(Long TicketCategoryId, UpdateTicketCategoryDTO updateTicketCategoryDTO) {
		TicketCategory newTicketCategory = new TicketCategory();

		newTicketCategory.setId(TicketCategoryId);
		newTicketCategory.setPrice(updateTicketCategoryDTO.price());
		newTicketCategory.setDescription(updateTicketCategoryDTO.description());
		newTicketCategory.setQuantity(updateTicketCategoryDTO.quantity());

		return ticketCategoryRepository.save(newTicketCategory);
	}

	// TODO implementar
	public void reserveTicket(Long ticketCategoryId, int quantity) {
		return;
	}
}
