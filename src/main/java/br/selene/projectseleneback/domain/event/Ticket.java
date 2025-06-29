package br.selene.projectseleneback.domain.event;

import java.time.Instant;
import java.time.LocalDateTime;

import br.selene.projectseleneback.domain.customer.Customer;
import br.selene.projectseleneback.domain.ticketCategory.TicketCategory;

public final class Ticket {

	private final int id;
	private final Event event;
	private final TicketCategory ticketCategory;
	private final Customer customer;
	private final TicketStatusEnum status;
	private final LocalDateTime createdAt;

	public Ticket(int id, Event event, TicketCategory ticketCategory, Customer customer, Instant expirationTime,
			TicketStatusEnum status, LocalDateTime createdAt) {
		this.id = id;
		this.event = event;
		this.ticketCategory = ticketCategory;
		this.customer = customer;
		this.status = status;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public Event getEvent() {
		return event;
	}

	public TicketCategory getTicketCategory() {
		return ticketCategory;
	}

	public Customer getCustomer() {
		return customer;
	}

	public TicketStatusEnum getStatus() {
		return status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

}
