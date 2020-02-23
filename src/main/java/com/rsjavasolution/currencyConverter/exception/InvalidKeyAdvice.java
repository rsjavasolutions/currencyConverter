package com.rsjavasolution.currencyConverter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

    @ControllerAdvice
public class InvalidKeyAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidKeyException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String keyNotFoundHandler(InvalidKeyException ex){
        return ex.getMessage();
    }

}
