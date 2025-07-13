package br.selene.projectseleneback.infra.services;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.selene.projectseleneback.domain.event.Address;
import br.selene.projectseleneback.domain.event.Event;
import br.selene.projectseleneback.domain.event.EventStatusEnum;
import br.selene.projectseleneback.domain.event.TicketCategory;
import br.selene.projectseleneback.domain.event.dto.CreateEventDTO;
import br.selene.projectseleneback.domain.event.dto.CreateTicketCategoryDTO;
import br.selene.projectseleneback.domain.event.dto.EventDTO;
import br.selene.projectseleneback.domain.event.dto.SearchEventDTO;
import br.selene.projectseleneback.domain.event.dto.TicketCategoryDTO;
import br.selene.projectseleneback.domain.event.dto.UpdateEventDTO;
import br.selene.projectseleneback.domain.event.dto.UpdateTicketCategoryDTO;
import br.selene.projectseleneback.domain.event.repository.IEventRepository;
import br.selene.projectseleneback.domain.event.service.IEventService;
import br.selene.projectseleneback.infra.exception.TicketOperationException;
import br.selene.projectseleneback.infra.objectStorage.IStorageService;

@Service
public class EventService implements IEventService {

	private IEventRepository eventRepository;
	private IStorageService storageService;

	public EventService(IEventRepository eventRepository, IStorageService storageService) {
		this.eventRepository = eventRepository;
		this.storageService = storageService;
	}

	@Override
	public Page<EventDTO> findAll(SearchEventDTO searchEventDTO) {
		Page<Event> page = eventRepository.findAll(searchEventDTO);

		List<EventDTO> ticketCategoriesDTO = page.getContent().stream().map(EventDTO::new).toList();

		Page<EventDTO> pageDTO = new PageImpl<>(ticketCategoriesDTO, page.getPageable(), page.getTotalElements());

		return pageDTO;
	}

	@Override
	public EventDTO findById(Long id) {
		return new EventDTO(eventRepository.findById(id));
	}

	@Override
	public EventDTO createEvent(CreateEventDTO createEventDTO,  MultipartFile file) throws IOException {
		Event newEvent = new Event();
		newEvent.setTitle(createEventDTO.getTitle());
		newEvent.setDescription(createEventDTO.getDescription());
		newEvent.setDate(createEventDTO.getDate());

		Address address = new Address();
		if (createEventDTO.getAddress() != null) {
			address.setCity(createEventDTO.getAddress().getCity());
			address.setNeighbourhood(createEventDTO.getAddress().getNeighbourhood());
			address.setNumber(createEventDTO.getAddress().getNumber());
			address.setState(createEventDTO.getAddress().getState());
			address.setStreet(createEventDTO.getAddress().getStreet());
			address.setZipCode(createEventDTO.getAddress().getZipCode());
		}

		newEvent.setAddress(address);
		newEvent.setStatus(EventStatusEnum.INACTIVE);
		
	    if (file != null && !file.isEmpty()) {
	    	String fileName = file.getOriginalFilename();
	        String newFileName = storageService.getName(fileName);
	        storageService.upload(newFileName, file.getInputStream(), file.getContentType());
	        newEvent.setPreviewImageUrl(newFileName);
	    }

		return new EventDTO(eventRepository.save(newEvent));
	}

	@Override
	public EventDTO updateEvent(Long eventId, UpdateEventDTO updateEventDTO, MultipartFile file) throws IOException {
		Event updatedEvent = new Event();

		updatedEvent.setId(eventId);
		if(updateEventDTO != null) {
			updatedEvent.setTitle(updateEventDTO.getTitle());
			updatedEvent.setDescription(updateEventDTO.getDescription());
			updatedEvent.setDate(updateEventDTO.getDate());
			updatedEvent.setStatus(EventStatusEnum.INACTIVE);

			Address address = new Address();
			if (updateEventDTO.getAddress() != null) {
				address.setCity(updateEventDTO.getAddress().getCity());
				address.setNeighbourhood(updateEventDTO.getAddress().getNeighbourhood());
				address.setNumber(updateEventDTO.getAddress().getNumber());
				address.setState(updateEventDTO.getAddress().getState());
				address.setStreet(updateEventDTO.getAddress().getStreet());
				address.setZipCode(updateEventDTO.getAddress().getZipCode());
			}

			updatedEvent.setAddress(address);
		}
		
		if (file != null && !file.isEmpty()) {
	    	String fileName = file.getOriginalFilename();
	        String newFileName = storageService.getName(fileName);
	        storageService.upload(newFileName, file.getInputStream(), file.getContentType());
	        updatedEvent.setPreviewImageUrl(newFileName);
	    }

		return new EventDTO(eventRepository.save(updatedEvent));
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
