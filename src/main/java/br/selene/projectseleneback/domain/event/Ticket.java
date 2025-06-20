package br.selene.projectseleneback.domain.event;

import java.time.Instant;
import java.time.LocalDateTime;

import br.selene.projectseleneback.domain.customer.Customer;

public class Ticket {

	private int id;
	private Event event;
	private TicketCategory ticketCategory;
	private Customer customer;
	private Instant expirationTime;
	private TicketStatusEnum status;
	private LocalDateTime createdAt;
	
	public Ticket(int id, Event event, TicketCategory ticketCategory, Customer customer, Instant expirationTime,
			TicketStatusEnum status, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.event = event;
		this.ticketCategory = ticketCategory;
		this.customer = customer;
		this.expirationTime = expirationTime;
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
	
	public Instant getExpirationTime() {
		return expirationTime;
	}
	
	public TicketStatusEnum getStatus() {
		return status;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
}
