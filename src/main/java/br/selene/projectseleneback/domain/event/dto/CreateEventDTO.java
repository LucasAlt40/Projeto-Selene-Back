package br.selene.projectseleneback.domain.event.dto;

import java.time.LocalDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateEventDTO {

    @NotBlank(message = "O titulo do evento é obrigatório")
    @Size(max = 255, message = "O título do evento deve ter no máximo 255 caracteres.")
    private String title;

    @NotBlank(message = "A descrição do evento é obrigatória")
    private String description;

    @NotNull(message = "A data do evento é obrigatória.")
    @Future(message = "A data do evento deve ser futura.")
    private LocalDateTime date;

    @Valid
    private AddressDTO address;
    
    public CreateEventDTO() {
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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
