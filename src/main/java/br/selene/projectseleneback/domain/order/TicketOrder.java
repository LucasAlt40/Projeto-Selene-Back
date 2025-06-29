package br.selene.projectseleneback.domain.order;

import br.selene.projectseleneback.domain.event.Event;
import br.selene.projectseleneback.domain.ticketCategory.TicketCategory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class TicketOrder {

	private int id;
	private TicketCategory category ;
	private LocalDateTime createdAt;
	private Event event;


	public TicketOrder() {}

	public TicketOrder(int id, TicketCategory category, LocalDateTime createdAt, Event event) {
		this.id = id;
		this.category = category;
		this.createdAt = createdAt;
		this.event = event;
	}

	public int getPrice() {
		return category.getPrice();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TicketCategory getCategory() {
		return category;
	}

	public void setCategory(TicketCategory category) {
		this.category = category;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
