package br.selene.projectseleneback.domain.event.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressDTO(

        @Size(max = 150, message = "A rua deve ter no máximo 150 caracteres.")
        String street,

        @Size(max = 20, message = "O número deve ter no máximo 20 caracteres.")
        String number,

        String zipCode,

        @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres.")
        String neighbourhood,

        @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres.")
        String city,

        @Size(min = 2, max = 2, message = "O estado deve ser uma sigla de 2 letras (ex: SP).")
        @Pattern(regexp = "^[A-Z]{2}$", message = "O estado deve conter apenas letras maiúsculas (ex: SP).")
        String state
) {}

