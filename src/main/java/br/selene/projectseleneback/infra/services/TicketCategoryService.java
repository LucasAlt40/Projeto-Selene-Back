package br.selene.projectseleneback.infra.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import br.selene.projectseleneback.domain.ticketCategory.TicketCategory;
import br.selene.projectseleneback.domain.ticketCategory.dto.CreateTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.SearchTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.TicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.dto.UpdateTicketCategoryDTO;
import br.selene.projectseleneback.domain.ticketCategory.repository.ITicketCategoryRepository;
import br.selene.projectseleneback.domain.ticketCategory.service.ITicketCategoryService;
import br.selene.projectseleneback.infra.exception.TicketOperationException;

@Service
public class TicketCategoryService implements ITicketCategoryService {

	private final ITicketCategoryRepository ticketCategoryRepository;

	public TicketCategoryService(ITicketCategoryRepository ticketCategoryRepository) {
		this.ticketCategoryRepository = ticketCategoryRepository;
	}

	@Override
	public Page<TicketCategoryDTO> findAll(SearchTicketCategoryDTO searchTicketCategoryDTO) {
		Page<TicketCategory> page = ticketCategoryRepository.findAll(searchTicketCategoryDTO);

		List<TicketCategoryDTO> ticketCategoriesDTO = page.getContent().stream().map(TicketCategoryDTO::new).toList();

		Page<TicketCategoryDTO> pageDTO = new PageImpl<>(ticketCategoriesDTO, page.getPageable(),
				page.getTotalElements());

		return pageDTO;
	}

	@Override
	public TicketCategoryDTO create(CreateTicketCategoryDTO createTicketCategoryDTO) {
		TicketCategory newTicketCategory = new TicketCategory();

		newTicketCategory.setPrice(createTicketCategoryDTO.price());
		newTicketCategory.setDescription(createTicketCategoryDTO.description());
		newTicketCategory.setQuantity(createTicketCategoryDTO.quantity());
		newTicketCategory.setQuantityAvaliable(createTicketCategoryDTO.quantity());

		return new TicketCategoryDTO(ticketCategoryRepository.save(newTicketCategory));
	}


	@Override
	public TicketCategoryDTO update(Long TicketCategoryId, UpdateTicketCategoryDTO updateTicketCategoryDTO) {
		TicketCategory newTicketCategory = new TicketCategory();

		newTicketCategory.setId(TicketCategoryId);
		newTicketCategory.setPrice(updateTicketCategoryDTO.price());
		newTicketCategory.setDescription(updateTicketCategoryDTO.description());
		newTicketCategory.setQuantity(updateTicketCategoryDTO.quantity());
		newTicketCategory.setQuantityAvaliable(updateTicketCategoryDTO.quantity());

		return new TicketCategoryDTO(ticketCategoryRepository.save(newTicketCategory));
	}

	@Override
	public void reserveTicket(Long ticketCategoryId, int quantity) {
		boolean updated = ticketCategoryRepository.reserveTicket(ticketCategoryId, quantity);
		if (!updated) {
			throw new TicketOperationException("Não foi possível reservar ticket, limite excedido ou esgotado");
		}
	}

	@Override
	public void releaseTicket(Long ticketCategoryId, int quantity) {
		boolean updated = ticketCategoryRepository.releaseTicket(ticketCategoryId, quantity);
		if (!updated) {
			throw new TicketOperationException("Não foi possível liberar ticket, limite máximo excedido");
		}
	}


}
