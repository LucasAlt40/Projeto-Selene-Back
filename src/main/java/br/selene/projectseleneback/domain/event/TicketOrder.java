package br.selene.projectseleneback.domain.event;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class TicketOrder {

	private int id;
	private List<TicketCategory> items;
	private Instant expirationTime;
	private TicketOrderStatusEnum status;
	private Double priceTotal;
	private LocalDateTime createdAt;

	public int getId() {
		return id;
	}

	public List<TicketCategory> getItems() {
		return items;
	}

	public Instant getExpirationTime() {
		return expirationTime;
	}

	public TicketOrderStatusEnum getStatus() {
		return status;
	}

	public Double getPriceTotal() {
		return priceTotal;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

}
