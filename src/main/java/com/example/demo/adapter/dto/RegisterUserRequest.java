package com.example.demo.adapter.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public record RegisterUserRequest(@NotBlank String name, @NotBlank  @CPF String cpf, @NotBlank @Email String email, @NotBlank String dataNascimento, @NotBlank @Length(max = 8, message = "Cep precisa ter 8 caracteres") String cep,
                                   AddressDTO endereco) {

}
