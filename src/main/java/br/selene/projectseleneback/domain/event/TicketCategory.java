package br.selene.projectseleneback.domain.event;

import java.time.LocalDateTime;

public class TicketCategory {

	private Long id;
	private Long price;
	private String description;
	private int quantity;
	private int quantityAvaliable;
	private Long eventId;
	private LocalDateTime createdAt;

	public TicketCategory() {
	}

	public TicketCategory(Long id, Long price, String description, int quantity, int quantityAvaliable, Long eventId,
			LocalDateTime createdAt) {
		this.id = id;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.quantityAvaliable = quantityAvaliable;
		this.eventId = eventId;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantityAvaliable() {
		return quantityAvaliable;
	}

	public void setQuantityAvaliable(int quantityAvailable) {
		this.quantityAvaliable = quantityAvailable;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
