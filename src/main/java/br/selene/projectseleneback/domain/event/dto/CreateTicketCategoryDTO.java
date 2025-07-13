package br.selene.projectseleneback.domain.event.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateTicketCategoryDTO(

    @NotNull(message = "O valor da categoria é obrigatório")
    @Positive(message = "O valor da categoria deve ser maior que zero")
    Long price,

    @NotBlank(message = "A descrição da categoria é obrigatória")
    String description,

    @NotNull(message = "A quantidade de ingressos é obrigatória")
    @Positive(message = "A quantidade deve ser maior que zero")
    Integer quantity

) {}
