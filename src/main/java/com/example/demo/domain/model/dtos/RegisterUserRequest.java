package com.example.demo.domain.model.dtos;


public record RegisterUserRequest(String name, String cpf, String document, String email, String data_nascimento, String cep,
                                  AddressDTO address) {

}
