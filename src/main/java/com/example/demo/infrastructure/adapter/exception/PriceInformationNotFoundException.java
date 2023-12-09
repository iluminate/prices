package com.example.demo.infrastructure.adapter.exception;

public class PriceInformationNotFoundException extends RuntimeException {

    public PriceInformationNotFoundException(String message) {
        super(message);
    }
}