package com.rsjavasolution.currencyConverter.controller;

import com.rsjavasolution.currencyConverter.model.Converter;
import com.rsjavasolution.currencyConverter.model.Currency;
import com.rsjavasolution.currencyConverter.model.Exchanger;
import com.rsjavasolution.currencyConverter.model.Reader;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/")
@RestController
public class CurrencyRest {

    @GetMapping("list")
    public List<Currency> getList() {
        Reader reader = new Reader();
        return reader.getCurrencyList();
    }

    @GetMapping("exchanger")
    public Exchanger getExchange(@RequestParam String from, String to, double amount) {

        Converter converter = new Converter(from, to, amount);
        return new Exchanger(from.toUpperCase(),
                to.toUpperCase(),
                converter.exchangeMoney());
    }
}
