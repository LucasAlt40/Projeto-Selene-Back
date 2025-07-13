package br.selene.projectseleneback.domain.event.dto;

import java.time.LocalDateTime;

import br.selene.projectseleneback.domain.event.Address;
import br.selene.projectseleneback.domain.event.Event;
import br.selene.projectseleneback.domain.event.EventStatusEnum;

public class EventDTO {

	private Long id;
	private String title;
	private String description;
	private LocalDateTime date;
	private Address address;
	private String previewImageUrl;
	private EventStatusEnum status;

	public EventDTO() {
	}

	public EventDTO(Event event) {
		this.id = event.getId();
		this.title = event.getTitle();
		this.description = event.getDescription();
		this.date = event.getDate();
		this.address = event.getAddress();
		this.previewImageUrl = event.getPreviewImageUrl();
		this.status = event.getStatus();
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

	public String getPreviewImageUrl() {
		return previewImageUrl;
	}

	public void setPreviewImageUrl(String previewImageUrl) {
		this.previewImageUrl = previewImageUrl;
	}

	public EventStatusEnum getStatus() {
		return status;
	}

	public void setStatus(EventStatusEnum status) {
		this.status = status;
	}

}
