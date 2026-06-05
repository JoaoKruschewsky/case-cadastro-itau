package com.example.demo.application.usecase;

import com.example.demo.adapter.dto.RegisterUserRequest;
import com.example.demo.application.exception.ValidationDataInputException;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValidationUseCase {



    public static void validationUser (RegisterUserRequest body) {

        validationDateValid(body.dataNascimento());
        validationDateNoFuture(body.dataNascimento());
        hasSpecialCaracteres(body.name());

    }

    private static void validationDateValid(String date) {
        DateTimeFormatter validFormatData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
           LocalDate parseToDate = LocalDate.parse(date, validFormatData);
        } catch (Exception e) {
            throw new ValidationDataInputException("Formato de data inválido", 406);
        }
    }

    private static void validationDateNoFuture(String date) {
        DateTimeFormatter validFormatData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parseToDate = LocalDate.parse(date, validFormatData);
        LocalDate today = LocalDate.now();
        if (!parseToDate.isBefore(today)) {
            throw new ValidationDataInputException("Data nao pode ser futura ", 406);
        }
    }

    private static void hasSpecialCaracteres(String name) {
        String normalized = Normalizer.normalize(name, Normalizer.Form.NFD);
        if (!normalized.matches("[\\p{ASCII}\\s]*")){
            throw new ValidationDataInputException("Nome nao pode ter caracteres especiais ", 406);
        }
    }
}
