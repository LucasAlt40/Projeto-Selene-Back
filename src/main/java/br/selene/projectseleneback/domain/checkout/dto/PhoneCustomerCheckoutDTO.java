package br.selene.projectseleneback.domain.checkout.dto;

public record PhoneCustomerCheckoutDTO(
        String country,
        String area,
        String number
){}

