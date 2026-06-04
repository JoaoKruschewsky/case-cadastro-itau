package com.example.demo.application.exception;

import lombok.Getter;

@Getter
public class ValidationDataInputException extends RuntimeException{

    private final Integer httpStatus;

    public ValidationDataInputException(String message, Integer httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}