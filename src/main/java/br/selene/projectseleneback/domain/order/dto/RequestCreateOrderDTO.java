package br.selene.projectseleneback.domain.order.dto;

import java.util.List;

public record RequestCreateOrderDTO(
        Long customerId,
        List<CreateTicketDTO> tickets
) {}

