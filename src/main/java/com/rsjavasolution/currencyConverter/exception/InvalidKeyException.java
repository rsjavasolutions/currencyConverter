package com.rsjavasolution.currencyConverter.exception;

public class InvalidKeyException extends RuntimeException {

    public InvalidKeyException(String message) {
        super("Invalid Free API Key: " + message);
    }
}
