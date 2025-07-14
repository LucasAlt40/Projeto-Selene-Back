package br.selene.projectseleneback.domain.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateCustomerDTO(

    @Pattern(regexp = "\\d{11}", message = "O documento deve conter 11 caracteres")
    String document,

    @Size(min = 2, max = 100, message = "O nome deve conter entre 2 e 100 caracteres")
    String name,

    @Email(message = "O e-mail deve ser válido")
    String email,

    @Pattern(regexp = "\\d{11}", message = "O numero de telefone deve conter 11 digitos")
    String phone,

    @Size(min = 6, message = "A senha deve  conter no mínimo 6 caracteres")
    String password

){}

