package br.selene.projectseleneback.domain.order;

public class ItemOrder {

	private Long orderId;
	private Long ticketCategoryId;
	private Long ticketCategoryPrice;
	private String ticketCategoryDescription;
	private int ticketCategoryQuantity;
	private Long eventId;

	public ItemOrder() {}

	public ItemOrder(Long orderId, Long ticketCategoryId, Long ticketCategoryPrice, String ticketCategoryDescription, int ticketCategoryQuantity, Long eventId) {
		this.orderId = orderId;
		this.ticketCategoryId = ticketCategoryId;
		this.ticketCategoryPrice = ticketCategoryPrice;
		this.ticketCategoryDescription = ticketCategoryDescription;
		this.ticketCategoryQuantity = ticketCategoryQuantity;
		this.eventId = eventId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getTicketCategoryId() {
		return ticketCategoryId;
	}

	public void setTicketCategoryId(Long ticketCategoryId) {
		this.ticketCategoryId = ticketCategoryId;
	}

	public Long getTicketCategoryPrice() {
		return ticketCategoryPrice;
	}

	public void setTicketCategoryPrice(Long ticketCategoryPrice) {
		this.ticketCategoryPrice = ticketCategoryPrice;
	}

	public String getTicketCategoryDescription() {
		return ticketCategoryDescription;
	}

	public void setTicketCategoryDescription(String ticketCategoryDescription) {
		this.ticketCategoryDescription = ticketCategoryDescription;
	}

	public int getTicketCategoryQuantity() {
		return ticketCategoryQuantity;
	}

	public void setTicketCategoryQuantity(int ticketCategoryQuantity) {
		this.ticketCategoryQuantity = ticketCategoryQuantity;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
}
