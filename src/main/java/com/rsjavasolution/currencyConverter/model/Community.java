package com.rsjavasolution.currencyConverter.model;

import lombok.Data;

@Data
public class Community {

    private int status;
    private String error;

    public Community(String error) {
        status = 400;
        this.error = error;
    }
}
