package com.example.demo.application.exception;


import lombok.Getter;

@Getter
public class UserException extends RuntimeException{

    private final Integer httpStatus;

    public UserException(String message, Integer httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
