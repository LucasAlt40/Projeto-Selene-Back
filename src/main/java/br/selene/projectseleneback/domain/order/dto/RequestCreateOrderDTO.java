package br.selene.projectseleneback.domain.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RequestCreateOrderDTO(

        @NotNull(message = "O id do cliente é obrigatório")
        Long customerId,

        @NotNull(message = "A lista de ingressos é obrigatória")
        @Size(min = 1, message = "A lista deve conter ingressos")
        @Valid
        List<CreateTicketDTO> tickets
) {}

