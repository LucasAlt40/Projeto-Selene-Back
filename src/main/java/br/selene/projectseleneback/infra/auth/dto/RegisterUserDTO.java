package br.selene.projectseleneback.infra.auth.dto;

public record RegisterUserDTO(String document, String name, String email, String phone, String password) {

}
