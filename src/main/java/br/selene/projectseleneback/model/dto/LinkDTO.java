package br.selene.projectseleneback.model.dto;

public record LinkDTO(
        String rel,
        String href,
        String method
) {}
