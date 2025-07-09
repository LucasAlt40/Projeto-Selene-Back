package br.selene.projectseleneback.domain.event.dto;

import java.time.LocalDateTime;

public record UpdateEventDTO(
        String title,
        String description,
        LocalDateTime date) {
}

