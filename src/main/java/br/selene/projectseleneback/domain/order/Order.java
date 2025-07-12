package br.selene.projectseleneback.domain.order;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

	private Long id;
	private Long customerId;
	private List<ItemOrder> items;
	private OrderStatusEnum status;
	private LocalDateTime createdAt;

	public Order() {
	}

	public Order(Long id, Long customerId, OrderStatusEnum status, List<ItemOrder> items, LocalDateTime createdAt) {
		this.id = id;
		this.status = status;
		this.customerId = customerId;
		this.items = items;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomer() {
		return customerId;
	}

	public void setCustomer(Long customerId) {
		this.customerId = customerId;
	}

	public OrderStatusEnum getStatus() {
		return status;
	}

	public void setStatus(OrderStatusEnum status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<ItemOrder> getItems() {
		return items;
	}

	public void setItems(List<ItemOrder> items) {
		this.items = items;
	}

	public double getTotalPrice() {
		return this.items.stream()
				.mapToDouble(ItemOrder::getPrice) // evita autoboxing de Double
				.sum();
	}

}
