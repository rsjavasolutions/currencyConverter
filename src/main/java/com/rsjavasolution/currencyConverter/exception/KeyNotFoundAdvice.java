package com.rsjavasolution.currencyConverter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class KeyNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(KeyNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String keyNotFoundHandler(KeyNotFoundException ex){
        return ex.getMessage();
    }

}
