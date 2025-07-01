package br.selene.projectseleneback.domain.order;

import java.time.LocalDateTime;
import java.util.List;

import br.selene.projectseleneback.domain.customer.Customer;

public class Order {
    private int id;
    private Customer customer;
    private OrderStatusEnum status;
    private LocalDateTime createdAt;
    private List<TicketOrder> items;

    public Order() {}

    public Order(int id, Customer customer,  OrderStatusEnum status, LocalDateTime createdAt, List<TicketOrder> items) {
        this.id = id;
        this.customer = customer;
        this.status = status;
        this.createdAt = createdAt;
        this.items = items;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getTotalPrice() {
        int total = 0;
        for (TicketOrder i : items) {
            total += i.getPrice();
        }
        return total;
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

    public List<TicketOrder> getItems() {
        return items;
    }

    public void setItems(List<TicketOrder> items) {
        this.items = items;
    }
}
