package br.selene.projectseleneback.domain.event.dto;

public class BuyEventTicketInDto {

	private int customerId;
	private int eventId;
	private int ticketCategoryId;
	private int ticketQuantity;
	
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public int getEventId() {
		return eventId;
	}
	
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	public int getTicketCategoryId() {
		return ticketCategoryId;
	}
	
	public void setTicketCategoryId(int ticketCategoryId) {
		this.ticketCategoryId = ticketCategoryId;
	}
	
	public int getTicketQuantity() {
		return ticketQuantity;
	}
	
	public void setTicketQuantity(int ticketQuantity) {
		this.ticketQuantity = ticketQuantity;
	}
	
}
