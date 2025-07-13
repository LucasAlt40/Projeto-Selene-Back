package br.selene.projectseleneback.domain.event.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class CreateEventForm {
    
    @Valid
    private CreateEventDTO event;

    @NotNull(message = "O arquivo da imagem é obrigatório.")
    private MultipartFile file;

    // Getters e setters
    public CreateEventDTO getEvent() {
        return event;
    }

    public void setEvent(CreateEventDTO event) {
        this.event = event;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
