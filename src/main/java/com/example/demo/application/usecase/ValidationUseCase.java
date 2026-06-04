package com.example.demo.application.usecase;

import com.example.demo.adapter.dto.RegisterUserRequest;
import com.example.demo.application.exception.ValidationDataInputException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValidationUseCase {



    public static void validationUser (RegisterUserRequest body) {

        DateTimeFormatter validFormatData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parseToDate;
        try {
             parseToDate = LocalDate.parse(body.dataNascimento(), validFormatData);
        } catch (Exception e) {
            throw new ValidationDataInputException("Formato de data inválido", 406);
        }

        LocalDate today = LocalDate.now();
        if (!parseToDate.isBefore(today)) {
            throw new ValidationDataInputException("Data nao pode ser futura ", 406);

        }

    }
}
