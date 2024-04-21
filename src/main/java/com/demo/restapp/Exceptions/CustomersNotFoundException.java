package com.demo.restapp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class CustomersNotFoundException extends RuntimeException{

    public CustomersNotFoundException(String message) {
        super(message);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleCustomerNotFoundException(CustomersNotFoundException e){
        ErrorMessage error = new ErrorMessage();

        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<ErrorMessage>(error, HttpStatus.NOT_FOUND);
    }
}
