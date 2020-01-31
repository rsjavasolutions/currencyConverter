package com.rsjavasolution.currencyConverter.model;
import lombok.Data;

@Data
public class Converter {
    private String from;
    private String to;
    private double amount;

    public Converter(String from, String to, double amount) {
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    public Converter() {
    }

    public double currencyValue() {
        NbpService nbpService = new NbpService();
        double fromValue = 0;
        double toValue = 0;

        for (Currency c : nbpService.getCurrencyList()) {
            if (from.equalsIgnoreCase("PLN")) {
                fromValue = 1;
            } if (from.equalsIgnoreCase(c.getCode())) {
                fromValue = c.getValue();
            } if (to.equalsIgnoreCase(c.getCode())) {
                toValue = c.getValue();
            } if (to.equalsIgnoreCase("PLN")) {
                toValue = 1.0;
            }
        }
        return fromValue / toValue;
    }

    public double exchangeMoney() {
        if (amount < 0) {
            amount = 1;
        }
        double value = amount * currencyValue();
        value *= 100;
        value = Math.round(value);
        value /= 100;
        return value;
    }
}
