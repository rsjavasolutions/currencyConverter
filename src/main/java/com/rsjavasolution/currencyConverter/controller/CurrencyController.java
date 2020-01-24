package com.rsjavasolution.currencyConverter.controller;

import com.rsjavasolution.currencyConverter.model.Converter;
import com.rsjavasolution.currencyConverter.model.Currency;
import com.rsjavasolution.currencyConverter.model.Reader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CurrencyController {

    private Converter converter;

    public CurrencyController() {
        this.converter = new Converter();
    }

    @GetMapping("/")
    public String getCurrency(ModelMap map) {
        Reader reader = new Reader();
        map.put("allCurrencies", reader.getCurrencyList());
        map.put("newExchange", new Converter());
        return "index";
    }

    @GetMapping("exchange")
    String getCalc(ModelMap map){
        map.put("showCourse",converter.exchangeMoney());
        map.put("showFromCode",converter.getFrom());
        map.put("showToCode", converter.getTo());
        map.put("showAmount", converter.getAmount());
        return "exchange";
    }

    @PostMapping("/add-currency")
    public String addOperation(@ModelAttribute Converter converter) {
        this.converter = converter;
        return "redirect:/exchange";
    }
}