package br.selene.projectseleneback.domain.ticket;

import java.time.LocalDateTime;

public class Ticket {
    private Long id;
    private String status;

    private Long customerId;
    private String customerDocument;
    private  String customerName;

    private Long eventId;
    private String eventTitle;
    private  String eventDescription;
    private LocalDateTime eventDate;

    private Long ticketCategoryId;
    private  Long ticketCategoryPrice;
    private String ticketCategoryDescription;

    private LocalDateTime createdAt;

    public Ticket() {}

    public Ticket(Long id, String status, Long customerId, String customerDocument, String customerName, Long eventId, String eventTitle, String eventDescription, LocalDateTime eventDate, Long ticketCategoryId, Long ticketCategoryPrice, String ticketCategoryDescription, LocalDateTime createdAt) {
        this.id = id;
        this.status = status;
        this.customerId = customerId;
        this.customerDocument = customerDocument;
        this.customerName = customerName;
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.ticketCategoryId = ticketCategoryId;
        this.ticketCategoryPrice = ticketCategoryPrice;
        this.ticketCategoryDescription = ticketCategoryDescription;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerDocument() {
        return customerDocument;
    }

    public void setCustomerDocument(String customerDocument) {
        this.customerDocument = customerDocument;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
