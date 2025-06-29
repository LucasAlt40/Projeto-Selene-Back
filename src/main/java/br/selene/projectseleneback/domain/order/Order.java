package br.selene.projectseleneback.domain.order;

import java.time.LocalDateTime;

import br.selene.projectseleneback.domain.customer.Customer;

public class Order {
    private int id;
    private String checkout_id;
    private Customer customer;
    private int totalPrice;
    private OrderStatusEnum status;
    private LocalDateTime createdAt;

    public Order() {}

    public Order(int id, String checkout_id, Customer customer, int totalPrice, OrderStatusEnum status, LocalDateTime createdAt) {
        this.id = id;
        this.checkout_id = checkout_id;
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheckout_id() {
        return checkout_id;
    }

    public void setCheckout_id(String checkout_id) {
        this.checkout_id = checkout_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
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
}
