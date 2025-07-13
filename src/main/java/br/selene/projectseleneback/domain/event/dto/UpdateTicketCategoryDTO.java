package br.selene.projectseleneback.domain.event.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateTicketCategoryDTO (

        @Positive(message = "O valor da categoria deve ser maior que zero")
        Long price,

        String description,

        @Positive(message = "A quantidade deve ser maior que zero")
        Integer quantity

){}
