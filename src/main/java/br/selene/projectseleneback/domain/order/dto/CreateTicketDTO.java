package br.selene.projectseleneback.domain.order.dto;

public record CreateTicketDTO(
        int eventId,
        int categoryId,
        int quantity
){
    
}
