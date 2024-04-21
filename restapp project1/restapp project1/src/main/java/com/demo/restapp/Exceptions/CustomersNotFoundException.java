package com.demo.restapp.Exceptions;

public class CustomersNotFoundException extends RuntimeException{

    public CustomersNotFoundException(String message) {
        super(message);
    }
}
