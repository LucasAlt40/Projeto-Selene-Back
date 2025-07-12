package br.selene.projectseleneback.domain.checkout.dto;

public record ResponseLinksDTO(
        String rel,
        String href,
        String method
){}
