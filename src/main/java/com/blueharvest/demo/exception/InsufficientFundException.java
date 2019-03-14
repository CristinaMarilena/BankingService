package com.blueharvest.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InsufficientFundException extends RuntimeException {

    private final String message;

    public InsufficientFundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}