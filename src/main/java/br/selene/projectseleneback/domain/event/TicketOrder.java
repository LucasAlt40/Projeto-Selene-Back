package br.selene.projectseleneback.domain.event;

import java.time.Instant;
import java.time.LocalDateTime;

public class TicketOrder {

	private int id;
	private TicketCategory ticketCategory;
	private int quantity;
	private Instant expirationTime;
	private Double price;
	private TicketOrderStatusEnum status;
	private LocalDateTime createdAt;
	
	public TicketOrder(int id, TicketCategory ticketCategory, int quantity, Instant expirationTime, Double price,
			TicketOrderStatusEnum status, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.ticketCategory = ticketCategory;
		this.quantity = quantity;
		this.expirationTime = expirationTime;
		this.price = price;
		this.status = status;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public TicketCategory getTicketCategory() {
		return ticketCategory;
	}

	public int getQuantity() {
		return quantity;
	}

	public Instant getExpirationTime() {
		return expirationTime;
	}

	public Double getPrice() {
		return price;
	}

	public TicketOrderStatusEnum getStatus() {
		return status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
}
