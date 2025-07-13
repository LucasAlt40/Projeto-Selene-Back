package br.selene.projectseleneback.domain.event;

import java.time.LocalDateTime;

public class Event {

	private Long id;
	private String title;
	private String description;
	private LocalDateTime date;
	private Address address;
	private EventStatusEnum status;
	private LocalDateTime createdAt;

	public Event() {
	}

	public Event(Long id, String title, String description, LocalDateTime date, Address address, EventStatusEnum status,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.address = address;
		this.status = status;

		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public EventStatusEnum getStatus() {
		return status;
	}

	public void setStatus(EventStatusEnum status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
