package br.selene.projectseleneback.domain.ticket.dto;

import java.time.LocalDateTime;

public record TicketResponseDto(
        Long id,
        String status,
        String customerDocument,
        String customerName,
        String eventTitle,
        String eventImage,
        String ticketCategoryDescription,
        LocalDateTime createdAt
) {
}
