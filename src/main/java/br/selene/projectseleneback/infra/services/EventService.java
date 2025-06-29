package br.selene.projectseleneback.infra.services;

import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.customer.repository.ICustomerRepository;
import br.selene.projectseleneback.domain.event.Event;
import br.selene.projectseleneback.domain.event.Ticket;
import br.selene.projectseleneback.domain.event.dto.BuyEventTicketInDto;
import br.selene.projectseleneback.domain.event.repository.IEventRepository;

public class EventService {

	private ICustomerRepository customerRepository;
	private IEventRepository eventRepository;

	public EventService(ICustomerRepository customerRepository, IEventRepository eventRepository) {
		this.customerRepository = customerRepository;
		this.eventRepository = eventRepository;
	}

	public void buyEventTicket(BuyEventTicketInDto buyEventTicketInDto) {
		Customer customer = customerRepository.findById(buyEventTicketInDto.getCustomerId());
		Event event = eventRepository.findById(buyEventTicketInDto.getEventId());
		// TO-DO
		// Assemble body
		// Subtract ticketCategory quantity
		// Redirect to Checkout
	}

	public Ticket generateEventTicket() {
		// TO-DO
		// Create a event ticket
		return null;
	}

}
