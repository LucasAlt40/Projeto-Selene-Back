package br.selene.projectseleneback.domain.event.dto;

import java.time.LocalDateTime;

public record CreateEventDTO(String title, String description, LocalDateTime date) {
}
