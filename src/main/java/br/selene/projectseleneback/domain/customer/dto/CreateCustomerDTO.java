package br.selene.projectseleneback.domain.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateCustomerDTO(

        @NotBlank(message = "O documento é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "O documento deve conter 11 caracteres")
        String document,

        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 2, max = 100, message = "O nome deve conter entre 2 e 100 caracteres")
        String name,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "O e-mail deve ser válido")
        String email,

        @NotBlank(message = "O numero de telefone é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "O numero de telefone deve conter 11 digitos")
        String phone,

        @Size(min = 6, message = "A senha deve  conter no mínimo 6 caracteres")
        String password

){}