package br.selene.projectseleneback.domain.order;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
	
    private int id;
    private int customerId;
    private List<ItemOrder> items;
    private OrderStatusEnum status;
    private LocalDateTime createdAt;

    public Order() {}

    public Order(int id, int customerId, OrderStatusEnum status, LocalDateTime createdAt) {
        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
        this.customerId = customerId;
    }


    public Order(int id, int customerId,  OrderStatusEnum status, LocalDateTime createdAt, List<ItemOrder> items) {
        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
        this.items = items;
        this.customerId = customerId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer() {
        return customerId;
    }

    public void setCustomer(int customerId) {
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
}
