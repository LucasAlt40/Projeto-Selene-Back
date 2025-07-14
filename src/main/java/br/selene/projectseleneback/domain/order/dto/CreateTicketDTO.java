package br.selene.projectseleneback.domain.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateTicketDTO(
        @NotNull(message = "O id do evento é obrigatorio")
        Long eventId,

        @NotNull(message = "O id da categoria é obrigatorio")
        Long categoryId,

        @Min(value = 1, message = "A quantidade deve ser maior que 1")
        int quantity
){
    
}
