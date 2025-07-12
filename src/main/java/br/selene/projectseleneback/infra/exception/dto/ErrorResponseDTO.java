package br.selene.projectseleneback.infra.exception.dto;

import java.util.List;

public class ErrorResponseDTO {
	private String message;
	private List<String> details;

	public ErrorResponseDTO() {
	}

	public ErrorResponseDTO(String message, List<String> details) {
		this.message = message;
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

}
