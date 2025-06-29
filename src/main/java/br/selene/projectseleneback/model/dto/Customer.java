package br.selene.projectseleneback.model.dto;

public record Customer(
        String name,
        String email,
        String tax_id,
        PhoneDTO phone
) {}
