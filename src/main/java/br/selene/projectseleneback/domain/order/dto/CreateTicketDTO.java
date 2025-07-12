package br.selene.projectseleneback.domain.order.dto;

public record CreateTicketDTO(
        Long eventId,
        Long categoryId,
        int quantity
){
    
}
