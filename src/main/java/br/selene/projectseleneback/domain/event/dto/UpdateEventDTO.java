package br.selene.projectseleneback.domain.event.dto;

import java.time.LocalDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;

public class UpdateEventDTO {

    @Size(max = 255, message = "O título do evento deve ter no máximo 255 caracteres.")
    private String title;

    private String description;

    @Future(message = "A data do evento deve ser futura.")
    private LocalDateTime date;

    @Valid
    private AddressDTO address;

    // Getters e Setters

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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
