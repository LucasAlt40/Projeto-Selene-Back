package br.selene.projectseleneback.domain.order;

import java.time.LocalDateTime;

public class ItemOrder {

	private int id;
	private String itemName;
	private int ticketCategoryId;
	private String ticketCategoryDescription;
	private long ticketCategoryPrice;
	private int ticketCategoryQuantity;
	private int eventId;
	private int quantity;
	private long price;
	private String imageUrl;

	private LocalDateTime createdAt;

	public ItemOrder() {
	}

	public ItemOrder(int id, int ticketCategoryId, int eventId, int quantity, LocalDateTime createdAt, String eventName, String ticketCategoryName, String imageUrl) {
		super();
		this.id = id;
		this.ticketCategoryId = ticketCategoryId;
		this.eventId = eventId;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.itemName = STR."\{eventName}_\{ticketCategoryName}";
		this.imageUrl = imageUrl;
	}

	public String getTicketCategoryDescription() {
		return ticketCategoryDescription;
	}

	public void setTicketCategoryDescription(String ticketCategoryDescription) {
		this.ticketCategoryDescription = ticketCategoryDescription;
	}

	public long getTicketCategoryPrice() {
		return ticketCategoryPrice;
	}

	public void setTicketCategoryPrice(long ticketCategoryPrice) {
		this.ticketCategoryPrice = ticketCategoryPrice;
	}

	public int getTicketCategoryQuantity() {
		return ticketCategoryQuantity;
	}

	public void setTicketCategoryQuantity(int ticketCategoryQuantity) {
		this.ticketCategoryQuantity = ticketCategoryQuantity;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTicketCategoryId() {
		return ticketCategoryId;
	}

	public void setTicketCategoryId(int ticketCategoryId) {
		this.ticketCategoryId = ticketCategoryId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
