package br.selene.projectseleneback.domain.checkout.dto;

public  record  ItemsCheckoutDTO(
        String name, 
        Integer quantity,
        Integer unit_amount,
        String imageURl
){}
