package br.selene.projectseleneback.domain.order.dto;

import java.util.List;

public record RequestCreateOrderDTO(
        int customerId,
        List<CreateTicketDTO> tickets
) {}

