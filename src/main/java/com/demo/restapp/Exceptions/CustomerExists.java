package com.demo.restapp.Exceptions;

public class CustomerExists extends RuntimeException {
    public CustomerExists(String message) {
        super(message);
    }

}
