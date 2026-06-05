package com.example.demo.application.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final Integer httpStatus;

    public ApiException(String message, Integer httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
