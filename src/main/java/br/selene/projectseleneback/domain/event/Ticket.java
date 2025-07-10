package br.selene.projectseleneback.domain.event;

import java.time.LocalDateTime;

public final class Ticket {

	private final Long id;
	private final Long eventId;
	private final String eventTitle;
	private final LocalDateTime eventDate;
	private final Long ticketCategoryId;
	private final int ticketCategoryPrice;
	private final String ticketCategoryDescription;
	private final Long customerId;
	private final String customerDocument;
	private final String customerName;
	private final TicketStatusEnum status;
	private final LocalDateTime createdAt;

	public Ticket(Long id, Long eventId, String eventTitle, LocalDateTime eventDate, Long ticketCategoryId,
			int ticketCategoryPrice, String ticketCategoryDescription, Long customerId, String customerDocument,
			String customerName, TicketStatusEnum status, LocalDateTime createdAt) {
		this.id = id;
		this.eventId = eventId;
		this.eventTitle = eventTitle;
		this.eventDate = eventDate;
		this.ticketCategoryId = ticketCategoryId;
		this.ticketCategoryPrice = ticketCategoryPrice;
		this.ticketCategoryDescription = ticketCategoryDescription;
		this.customerId = customerId;
		this.customerDocument = customerDocument;
		this.customerName = customerName;
		this.status = status;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public Long getEventId() {
		return eventId;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public Long getTicketCategoryId() {
		return ticketCategoryId;
	}

	public int getTicketCategoryPrice() {
		return ticketCategoryPrice;
	}

	public String getTicketCategoryDescription() {
		return ticketCategoryDescription;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public String getCustomerDocument() {
		return customerDocument;
	}

	public String getCustomerName() {
		return customerName;
	}

	public TicketStatusEnum getStatus() {
		return status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

}
