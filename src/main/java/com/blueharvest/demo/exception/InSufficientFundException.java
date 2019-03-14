package com.blueharvest.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InSufficientFundException extends RuntimeException {

    private String message;

    public InSufficientFundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}