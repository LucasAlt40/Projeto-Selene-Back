package br.selene.projectseleneback.domain.order;

import java.time.LocalDateTime;

public class ItemOrder {

	private int id;
	private String itemName;
	private int ticketCategoryId;
	private int eventId;
	private int quantity;
	private int price;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
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
