package br.selene.projectseleneback.model.dto;

import java.util.List;

public record RequestCheckoutDTO(
        Customer customer,
        String paymentMethod,
        List<Items> items
) {
}

