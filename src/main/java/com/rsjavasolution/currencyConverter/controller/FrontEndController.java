package com.rsjavasolution.currencyConverter.controller;

import com.rsjavasolution.currencyConverter.model.Converter;
import com.rsjavasolution.currencyConverter.model.NbpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FrontEndController {

    private Converter converter;

    public FrontEndController() {
        this.converter = new Converter();
    }

    @GetMapping("/")
    public String getCurrency(ModelMap map) {
        NbpService nbpService = new NbpService();
        map.put("allCurrencies", nbpService.getCurrencyList());
        map.put("newExchange", new Converter());
        return "index";
    }

    @GetMapping("/api")
    public String getApi(){
        return "api";
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