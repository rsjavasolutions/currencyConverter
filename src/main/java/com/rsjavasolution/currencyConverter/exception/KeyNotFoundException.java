package com.rsjavasolution.currencyConverter.exception;

public class KeyNotFoundException extends RuntimeException {

    public KeyNotFoundException() {
        super("API Key is required");

    }
}
