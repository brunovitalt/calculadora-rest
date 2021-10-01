package com.example.calculadora.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class exception1 extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public exception1 (String exception) {
        super(exception);
    }
}
