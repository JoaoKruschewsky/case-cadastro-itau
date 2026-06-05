package com.example.demo.adapter.out.repository.mapper;

import com.example.demo.adapter.dto.RegisterUserRequest;
import com.example.demo.domain.model.entity.Address;
import com.example.demo.domain.model.entity.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserMapper {


    public static User parseToUser (RegisterUserRequest body, String login) {
        DateTimeFormatter validFormatData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parseDate = LocalDate.parse(body.dataNascimento(), validFormatData);
        Address parseToAddres = Address.builder()
                .logradouro(body.endereco().logradouro())
                .estado(body.endereco().estado())
                .bairro(body.endereco().bairro())
                .uf(body.endereco().uf()).build();

        User newUser = User.builder()
                .cep(body.cep())
                .CPF(body.cpf())
                .nomeCompleto(body.name().trim())
                .email(body.email())
                .dataNascimento(parseDate)
                .loginName(login)
                .endereco(parseToAddres).build();


        return newUser;

    }
}
