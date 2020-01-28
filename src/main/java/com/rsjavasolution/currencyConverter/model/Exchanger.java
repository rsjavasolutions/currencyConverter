package com.rsjavasolution.currencyConverter.model;

import lombok.Data;

@Data
public class Exchanger {

    private String from;
    private String to;
    private Double value;

    public Exchanger(String from, String to, Double amount) {
        this.from = from;
        this.to = to;
        this.value = amount;
    }
}
