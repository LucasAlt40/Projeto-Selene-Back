package br.selene.projectseleneback.domain.event.dto;

import br.selene.projectseleneback.domain.event.TicketCategory;

public class TicketCategoryDTO {

	private Long id;
	private Long price;
	private String description;
	private int quantity;
	private int quantityAvaliable;

	public TicketCategoryDTO() {
	}

	public TicketCategoryDTO(TicketCategory ticketCategory) {
		this.id = ticketCategory.getId();
		this.price = ticketCategory.getPrice();
		this.description = ticketCategory.getDescription();
		this.quantity = ticketCategory.getQuantity();
		this.quantityAvaliable = ticketCategory.getQuantityAvaliable();
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

	public void setQuantityAvaliable(int quantityAvaliable) {
		this.quantityAvaliable = quantityAvaliable;
	}

}
