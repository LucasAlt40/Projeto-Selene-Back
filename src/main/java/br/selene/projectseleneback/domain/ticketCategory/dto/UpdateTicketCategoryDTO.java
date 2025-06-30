package br.selene.projectseleneback.domain.ticketCategory.dto;

import java.time.LocalDateTime;

public record UpdateTicketCategoryDTO (int price, String description, int quantity){
}
