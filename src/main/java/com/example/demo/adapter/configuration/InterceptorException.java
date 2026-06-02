package com.example.demo.adapter.configuration;

import com.example.demo.application.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.InternalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
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
    private ResponseEntity<ProblemDetail> handlerValidationException(MethodArgumentNotValidException e) {

        ProblemDetail serviceResponseError = ProblemDetail.forStatus(401);
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            serviceResponseError.setDetail("Campo: " + f.getField() + "Error: " + f.getDefaultMessage() );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(serviceResponseError);
    }
    @ExceptionHandler(InternalException.class)
    private ResponseEntity<ProblemDetail> handlerException(InternalException e) {

        ProblemDetail serviceResponseError = ProblemDetail.forStatus(500);
        serviceResponseError.setDetail(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(serviceResponseError);
    }

}