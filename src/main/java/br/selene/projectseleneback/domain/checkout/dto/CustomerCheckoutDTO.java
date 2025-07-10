package br.selene.projectseleneback.domain.checkout.dto;

public record CustomerCheckoutDTO(
        String name,
        String email,
        String tax_id,
        PhoneCustomerCheckoutDTO phone
) {}


