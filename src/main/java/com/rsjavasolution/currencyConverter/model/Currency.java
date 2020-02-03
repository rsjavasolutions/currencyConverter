package com.rsjavasolution.currencyConverter.model;

import lombok.Data;

@Data
public class Currency {

    private String name;
    private String code;
    private Double value;


    public Currency(String name, String code, Double value) {
        this.name = name;
        this.code = code;
        this.value = value;
    }



    @Override
    public String toString() {
        return "Kurs waluty: " + name
                + " Kod walutowy: " + code
                + " wynosi: " + value + "\n";
    }
}
