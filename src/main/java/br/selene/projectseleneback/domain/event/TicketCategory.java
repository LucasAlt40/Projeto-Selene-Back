package br.selene.projectseleneback.domain.event;

import java.time.LocalDateTime;

public class TicketCategory {

	private int id;
	private int price;
	private String description;
	private int quantity;
	private int quantityAvaliable;
	private LocalDateTime createdAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public void setQuantityAvaliable(int quantityAvaliable) {
		this.quantityAvaliable = quantityAvaliable;
	}
}
