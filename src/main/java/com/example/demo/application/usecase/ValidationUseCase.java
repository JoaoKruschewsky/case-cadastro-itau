package com.example.demo.application.usecase;

import com.example.demo.adapter.dto.RegisterUserRequest;
import com.example.demo.application.exception.ValidationDataInputException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class ValidationUseCase {



    public static void validationUser (RegisterUserRequest body) {

        LocalDate parseToData = LocalDate.parse(body.dataNascimento());
        SimpleDateFormat validFormatData = new SimpleDateFormat("dd/MM/yyyy");

        if (!validFormatData.equals(parseToData)) {
            throw new ValidationDataInputException("Formato de data inválido ", 406);
        }

        LocalDate today = LocalDate.now();
        if (!parseToData.isBefore(today)) {
            throw new ValidationDataInputException("Data nao pode ser futura ", 406);

        }

        String regexCep = "^\\d{5}-?\\d{3}$";
        if (!body.cep().equals(regexCep)) {
            throw new ValidationDataInputException("Formato Inválido CEP", 406);

        }
    }
}
