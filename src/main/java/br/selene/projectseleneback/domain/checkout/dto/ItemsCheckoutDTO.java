package br.selene.projectseleneback.domain.checkout.dto;

public  record  ItemsCheckoutDTO(
        String name, 
        Integer quantity,
        Long unit_amount,
        String imageURl
){}
