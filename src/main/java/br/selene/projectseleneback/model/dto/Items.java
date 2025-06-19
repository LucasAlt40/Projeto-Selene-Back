package br.selene.projectseleneback.model.dto;

import java.math.BigDecimal;

public record Items(
        String name,
        int quantity,
        BigDecimal unit_amount,
        String imageUrl
) {}
