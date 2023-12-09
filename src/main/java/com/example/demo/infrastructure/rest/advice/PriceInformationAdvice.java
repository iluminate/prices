package com.example.demo.infrastructure.rest.advice;

import com.example.demo.infrastructure.adapter.exception.PriceInformationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PriceInformationAdvice {

    @ExceptionHandler(PriceInformationNotFoundException.class)
    public ResponseEntity<String> handlePricingInformationNotFoundException(PriceInformationNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}