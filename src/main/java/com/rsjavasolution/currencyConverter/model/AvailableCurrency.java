package com.rsjavasolution.currencyConverter.model;

import lombok.Data;

@Data
public class AvailableCurrency {

    private String code;
    private String name;

    public AvailableCurrency(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
