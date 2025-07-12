package br.selene.projectseleneback.domain.order.dto;

public record ItemOrderDTO(
        String ticketCategoryDescription,
        int ticketCategoryQuantity,
        Long eventId,
        Long ticketCategoryPrice
) {}
