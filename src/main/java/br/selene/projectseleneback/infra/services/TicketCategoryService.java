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

@Service
public class TicketCategoryService implements ITicketCategoryService {

	private final ITicketCategoryRepository ticketCategoryRepository;

	public TicketCategoryService(ITicketCategoryRepository ticketCategoryRepository) {
		this.ticketCategoryRepository = ticketCategoryRepository;
	}

	public Page<TicketCategoryDTO> findAll(SearchTicketCategoryDTO searchTicketCategoryDTO) {
	    Page<TicketCategory> page = ticketCategoryRepository.findAll(searchTicketCategoryDTO);

	    List<TicketCategoryDTO> ticketCategoriesDTO = page.getContent().stream()
	        .map(TicketCategoryDTO::new)
	        .toList();

	    Page<TicketCategoryDTO> pageDTO = new PageImpl<>(
	        ticketCategoriesDTO,
	        page.getPageable(),
	        page.getTotalElements()
	    );

	    return pageDTO;
	}

	public TicketCategoryDTO create(CreateTicketCategoryDTO createTicketCategoryDTO) {
		TicketCategory newTicketCategory = new TicketCategory();

		newTicketCategory.setPrice(createTicketCategoryDTO.price());
		newTicketCategory.setDescription(createTicketCategoryDTO.description());
		newTicketCategory.setQuantity(createTicketCategoryDTO.quantity());
		newTicketCategory.setQuantityAvaliable(createTicketCategoryDTO.quantity());

		return new TicketCategoryDTO(ticketCategoryRepository.save(newTicketCategory));
	}

	public TicketCategoryDTO update(Long TicketCategoryId, UpdateTicketCategoryDTO updateTicketCategoryDTO) {
		TicketCategory newTicketCategory = new TicketCategory();

		newTicketCategory.setId(TicketCategoryId);
		newTicketCategory.setPrice(updateTicketCategoryDTO.price());
		newTicketCategory.setDescription(updateTicketCategoryDTO.description());
		newTicketCategory.setQuantity(updateTicketCategoryDTO.quantity());
		newTicketCategory.setQuantityAvaliable(updateTicketCategoryDTO.quantity());

		return new TicketCategoryDTO(ticketCategoryRepository.save(newTicketCategory));
	}

	// TODO implementar
	public void reserveTicket(Long ticketCategoryId, int quantity) {
		return;
	}
}
