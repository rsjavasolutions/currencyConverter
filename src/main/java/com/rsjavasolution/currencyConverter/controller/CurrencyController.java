package com.rsjavasolution.currencyConverter.controller;
import com.rsjavasolution.currencyConverter.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/api/")
@RestController
public class CurrencyController {

    String key = Constant.API_KEY;

    private NbpService nbpService = new NbpService();

    @GetMapping("currencies")
    public Object getList(@RequestParam(defaultValue = "empty") String apiKey) {
        if (apiKey.equals(key)) {
            return nbpService.getCurrencyList();
        } else if (apiKey.equals("empty")) {
            return new Community("API Key is required");
        } else {
            return new Community("Invalid Free API Key");
        }
    }

    @GetMapping("convert")
    public Object getExchange(
            @RequestParam (defaultValue = "empty") String apiKey ,
            String from, String to, double amount) {

        Converter converter = new Converter(from, to, amount);
        if (apiKey.equals(key)) {
            return new Exchanger(from.toUpperCase(),
                    to.toUpperCase(),
                    converter.exchangeMoney());
        } else if (apiKey.equals("empty")) {
            return new Community("API Key is required");
        } else {
            return new Community("Invalid Free API Key");

        }
    }

    @GetMapping("currencies/codes")
    public Object getAvailableCurrencyList(@RequestParam(defaultValue = "empty") String apiKey){
            if (apiKey.equals(key)) {
                return nbpService.getAvailableCurrencyList();
            } else if (apiKey.equals("empty")) {
                return new Community("API Key is required");
            } else {
                return new Community("Invalid Free API Key");
            }
        }
    }
