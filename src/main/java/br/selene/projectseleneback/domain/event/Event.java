package br.selene.projectseleneback.domain.event;

import java.time.LocalDateTime;
import java.util.List;

import br.selene.projectseleneback.domain.ticketCategory.TicketCategory;

public class Event {

	private int id;
	private String title;
	private String description;
	private LocalDateTime date;
	private EventStatusEnum status;
	private List<TicketCategory> tickets;
	private LocalDateTime createdAt;

	public Event(int id, String title, String description, LocalDateTime date, EventStatusEnum status, List<TicketCategory> tickets, LocalDateTime createdAt) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.status = status;
		this.tickets = tickets;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public EventStatusEnum getStatus() {
		return status;
	}

	public void setStatus(EventStatusEnum status) {
		this.status = status;
	}

	public List<TicketCategory> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketCategory> tickets) {
		this.tickets = tickets;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
