package br.selene.projectseleneback.infra.services;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import br.selene.projectseleneback.domain.customer.repository.ICustomerRepository;
import br.selene.projectseleneback.domain.event.TicketCategory;
import br.selene.projectseleneback.domain.event.dto.CreateTicketCategoryDTO;
import br.selene.projectseleneback.domain.event.dto.TicketCategoryDTO;
import br.selene.projectseleneback.domain.event.dto.UpdateTicketCategoryDTO;
import br.selene.projectseleneback.domain.event.repository.IEventRepository;
import br.selene.projectseleneback.domain.event.service.IEventService;
import br.selene.projectseleneback.infra.exception.TicketOperationException;

@Service
public class EventService implements IEventService {

	private IEventRepository eventRepository;

	public EventService(ICustomerRepository customerRepository, IEventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@Override
	public List<TicketCategoryDTO> findTicketCategoriesFromEvent(Long eventId) {
		Collection<TicketCategory> ticketCategories = eventRepository.findTicketCategoriesFromEvent(eventId);
		List<TicketCategoryDTO> ticketCategoriesDTO = ticketCategories.stream().map(TicketCategoryDTO::new).toList();
		return ticketCategoriesDTO;
	}

	@Override
	public TicketCategoryDTO findTicketCategoryById(Long ticketCategoryId) {
		TicketCategory ticketCategory = eventRepository.findTicketCategoryById(ticketCategoryId);
		return new TicketCategoryDTO(ticketCategory);
	}

	@Override
	public TicketCategoryDTO addTicketCategory(Long eventId, CreateTicketCategoryDTO createTicketCategoryDTO) {
		TicketCategory newTicketCategory = new TicketCategory();

		newTicketCategory.setEventId(eventId);
		newTicketCategory.setPrice(createTicketCategoryDTO.price());
		newTicketCategory.setDescription(createTicketCategoryDTO.description());
		newTicketCategory.setQuantity(createTicketCategoryDTO.quantity());
		newTicketCategory.setQuantityAvaliable(createTicketCategoryDTO.quantity());

		return new TicketCategoryDTO(eventRepository.addTicketCategory(newTicketCategory));
	}

	@Override
	public TicketCategoryDTO updateTicketCategory(Long eventId, Long ticketCategoryId,
			UpdateTicketCategoryDTO updateTicketCategoryDTO) {
		TicketCategory newTicketCategory = new TicketCategory();

		newTicketCategory.setId(ticketCategoryId);
		newTicketCategory.setEventId(eventId);
		newTicketCategory.setPrice(updateTicketCategoryDTO.price());
		newTicketCategory.setDescription(updateTicketCategoryDTO.description());
		newTicketCategory.setQuantity(updateTicketCategoryDTO.quantity());
		newTicketCategory.setQuantityAvaliable(updateTicketCategoryDTO.quantity());

		return new TicketCategoryDTO(eventRepository.updateTicketCategory(newTicketCategory));
	}

	@Override
	public void reserveTicket(Long ticketCategoryId, int quantity) throws TicketOperationException {
		boolean updated = eventRepository.reserveTicket(ticketCategoryId, quantity);
		if (!updated) {
			throw new TicketOperationException("Não foi possível reservar ticket, limite excedido ou esgotado");
		}

	}

	@Override
	public void releaseTicket(Long ticketCategoryId, int quantity) throws TicketOperationException {
		boolean updated = eventRepository.releaseTicket(ticketCategoryId, quantity);
		if (!updated) {
			throw new TicketOperationException("Não foi possível liberar ticket, limite máximo excedido");
		}

	}

}
