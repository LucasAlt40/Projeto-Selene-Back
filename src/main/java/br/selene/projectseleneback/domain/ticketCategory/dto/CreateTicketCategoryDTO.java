package br.selene.projectseleneback.domain.ticketCategory.dto;

import java.time.LocalDateTime;

public record CreateTicketCategoryDTO (int price, String description, int quantity){
}
