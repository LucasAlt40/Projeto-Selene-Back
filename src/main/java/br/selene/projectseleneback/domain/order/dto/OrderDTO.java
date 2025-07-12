package br.selene.projectseleneback.domain.order.dto;

import java.util.List;

public record OrderDTO(
        Long id,
        List<ItemOrderDTO> items,
        String status
) {}