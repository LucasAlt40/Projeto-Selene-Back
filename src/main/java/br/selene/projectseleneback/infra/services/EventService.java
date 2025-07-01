package br.selene.projectseleneback.infra.services;

import br.selene.projectseleneback.domain.customer.repository.ICustomerRepository;
import br.selene.projectseleneback.domain.event.repository.IEventRepository;

public class EventService {

	private ICustomerRepository customerRepository;
	private IEventRepository eventRepository;

	public EventService(ICustomerRepository customerRepository, IEventRepository eventRepository) {
		this.customerRepository = customerRepository;
		this.eventRepository = eventRepository;
	}



}
