package com.example.demo.adapter.out.repository.mapper;

import com.example.demo.adapter.dto.RegisterUserRequest;
import com.example.demo.domain.model.Address;
import com.example.demo.domain.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserMapper {


    public static User parseToUser (RegisterUserRequest body, String login) {
        DateTimeFormatter validFormatData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parseDate = LocalDate.parse(body.dataNascimento(), validFormatData);
        Address parseToAddres = new Address(
            body.endereco().logradouro(),
            body.endereco().bairro(),
            body.endereco().estado(),
            body.endereco().uf()
        );

        return new User(
                null,
                body.name(),
                body.cpf(),
                body.email(),
                parseDate,
                body.cep(),
                login,
                parseToAddres);

    }
}
