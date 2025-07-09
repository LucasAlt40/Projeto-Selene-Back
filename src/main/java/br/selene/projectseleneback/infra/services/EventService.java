package br.selene.projectseleneback.infra.services;

import br.selene.projectseleneback.domain.customer.repository.ICustomerRepository;
import br.selene.projectseleneback.domain.event.Event;
import br.selene.projectseleneback.domain.event.dto.CreateEventDTO;
import br.selene.projectseleneback.domain.event.dto.UpdateEventDTO;
import br.selene.projectseleneback.domain.event.repository.IEventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventService {

	private ICustomerRepository customerRepository;
	private IEventRepository eventRepository;

	public EventService(ICustomerRepository customerRepository, IEventRepository eventRepository) {
		this.customerRepository = customerRepository;
		this.eventRepository = eventRepository;
	}



	public Page<EventService> findAll(Pageable pageable){
		// TO-DO abstract and simplify pagination
		if(pageable == null) {
			pageable = new Pageable() {

				@Override
				public int getPageNumber() {
					return 1;
				}

				@Override
				public int getPageSize() {
					return 10;
				}

				@Override
				public long getOffset() {
					return 0;
				}

				@Override
				public Sort getSort() {
					return null;
				}

				@Override
				public Pageable next() {
					return null;
				}

				@Override
				public Pageable previousOrFirst() {
					return null;
				}

				@Override
				public Pageable first() {
					return null;
				}

				@Override
				public Pageable withPage(int pageNumber) {
					return null;
				}

				@Override
				public boolean hasPrevious() {
					return false;
				}

			};
		}
		return eventRepository.findAll(pageable);
	}

	public Event create(CreateEventDTO createEventDTO) {
		Event newEvent = new Event();

		newEvent.setTitle(createEventDTO.title());
		newEvent.setDescription(createEventDTO.description());
		newEvent.setDate(createEventDTO.date());
		newEvent.setTickets(createEventDTO.tickets());


		return eventRepository.save(newEvent);
	}

	public Event update(int eventId, UpdateEventDTO updateEventDTO) {
		Event newEvent = new Event();

		newEvent.setId(eventId);
		newEvent.setTitle(updateEventDTO.title());
		newEvent.setDescription(updateEventDTO.description());
		newEvent.setDate(updateEventDTO.date());
		newEvent.setTickets(updateEventDTO.tickets());

		return eventRepository.save(newEvent);
	}

}
