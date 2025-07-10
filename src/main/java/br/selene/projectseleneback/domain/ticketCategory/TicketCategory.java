package br.selene.projectseleneback.domain.ticketCategory;

import java.time.LocalDateTime;

public class TicketCategory {

	private Long id;
	private int price;
	private String description;
	private int quantity;
	private LocalDateTime createdAt;
	private int quantityAvaliable;

	public TicketCategory() {
	}

	public TicketCategory(Long id, int price, String description, int quantity, LocalDateTime createdAt,
			int quantityAvaliable) {
		this.id = id;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.quantityAvaliable = quantityAvaliable;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public int getQuantityAvaliable() {
		return quantityAvaliable;
	}

	public void setQuantityAvaliable(int quantityAvailable) {
		this.quantityAvaliable = quantityAvailable;
	}
}
