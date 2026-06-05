package com.example.demo.adapter.configuration;

import com.example.demo.application.exception.ApiException;
import com.example.demo.application.exception.UserException;
import com.example.demo.application.exception.ValidationDataInputException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class InterceptorException {

    private static final Logger logger = LoggerFactory.getLogger(InterceptorException.class);


    @ExceptionHandler(UserException.class)
    public ResponseEntity<ProblemDetail> handlerUserException(UserException e) {
        logger.error("UserException: {} ", e.getMessage(), e);

        HttpStatus status = e.getHttpStatus() != null ? HttpStatus.valueOf(e.getHttpStatus()) : HttpStatus.INTERNAL_SERVER_ERROR;

        ProblemDetail serviceResponseError = ProblemDetail.forStatus(status);
        serviceResponseError.setDetail(e.getMessage());
        return ResponseEntity.status(status).body(serviceResponseError);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ProblemDetail> handlerArgumentNotValidException(MethodArgumentNotValidException e) {

        ProblemDetail serviceResponseError = ProblemDetail.forStatus(406);
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            if (f.getField().contains("email")) {
                serviceResponseError.setDetail("Email inválido" );
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(serviceResponseError);

            }
            if (f.getField().contains("cpf")) {
                serviceResponseError.setDetail("CPF inválido" );
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(serviceResponseError);

            }
            serviceResponseError.setDetail("Campo: " + f.getField() + " Error: " + f.getDefaultMessage() );
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(serviceResponseError);
    }
    @ExceptionHandler(ApiException.class)
    private ResponseEntity<ProblemDetail> handlerException(ApiException e) {

        ProblemDetail serviceResponseError = ProblemDetail.forStatus(e.getHttpStatus());
        serviceResponseError.setDetail(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResponseError);
    }
    @ExceptionHandler(ValidationDataInputException.class)
    private ResponseEntity<ProblemDetail> handlerValidationDataInputException(ValidationDataInputException e) {

        ProblemDetail serviceResponseError = ProblemDetail.forStatus(e.getHttpStatus());
        serviceResponseError.setDetail(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(serviceResponseError);
    }

}