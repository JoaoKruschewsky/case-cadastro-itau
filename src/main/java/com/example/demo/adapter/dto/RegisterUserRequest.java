package com.example.demo.adapter.dto;


import jakarta.validation.constraints.NotBlank;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public record RegisterUserRequest(@NotBlank String name, @NotBlank String cpf, @NotBlank String email, @NotBlank String dataNascimento, @NotBlank String cep,
                                  AddressDTO endereco) {

}
