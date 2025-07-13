package br.selene.projectseleneback.domain.event.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

public class UpdateEventForm {

    @Valid
    private UpdateEventDTO event;

    private MultipartFile file; // pode ser null se não quiser atualizar a imagem

    public UpdateEventDTO getEvent() {
        return event;
    }

    public void setEvent(UpdateEventDTO event) {
        this.event = event;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}

