package com.rsjavasolution.currencyConverter.controller;

import com.rsjavasolution.currencyConverter.model.Converter;
import com.rsjavasolution.currencyConverter.model.Currency;
import com.rsjavasolution.currencyConverter.model.Exchanger;
import com.rsjavasolution.currencyConverter.model.Reader;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/api/")
@RestController
public class CurrencyController {

    private  Reader reader = new Reader();

    @GetMapping("list")
    public List<Currency> getList() {

        return reader.getCurrencyList();
    }

    @GetMapping("exchanger")
    public Exchanger getExchange(@RequestParam String from, String to, double amount) {

        Converter converter = new Converter(from, to, amount);
        return new Exchanger(from.toUpperCase(),
                to.toUpperCase(),
                converter.exchangeMoney());
    }

    @GetMapping("currency")
    public Map<String,String> getCurrencyMap(){
        return reader.getCurrencyList()
                .stream()
                .collect(Collectors.toMap(x -> x.getCode(), x -> x.getName()));
    }
}
