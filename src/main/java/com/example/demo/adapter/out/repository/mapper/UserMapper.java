package com.example.demo.adapter.out.repository.mapper;

import com.example.demo.domain.model.dtos.RegisterUserRequest;
import com.example.demo.domain.model.entity.Address;
import com.example.demo.domain.model.entity.User;

import java.time.LocalDate;

public class UserMapper {


    public static User parseToUser (RegisterUserRequest body) {

        Address parseToAddres = Address.builder()
                .logradouro(body.address().logradouro())
                .estado(body.address().estado())
                .bairro(body.address().bairro())
                .uf(body.address().uf()).build();

        return  User.builder()
                .cep(body.cep())
                .CPF(body.cpf())
                .email(body.email())
                .dataNascimento(LocalDate.parse(body.data_nascimento()))
                .documento(body.document())
                .endereco(parseToAddres).build();

    }
}
