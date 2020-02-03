package com.rsjavasolution.currencyConverter.controller;
import com.rsjavasolution.currencyConverter.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/api/")
@RestController
public class CurrencyController {

    String key = "4817cf3c05403b8f8e51";

    private NbpService nbpService = new NbpService();

    @GetMapping("currencies")
    public List<Currency> getList() {

        return nbpService.getCurrencyList();
    }

    //(defaultValue = "test")

    @GetMapping("convert")
    public Object getExchange(@RequestParam (defaultValue = "empty") String apiKey , String from, String to,
                                 double amount) {
        Converter converter = new Converter(from, to, amount);
        String klucz = apiKey;
        if (klucz.equals(key)) {
            return new Exchanger(from.toUpperCase(),
                    to.toUpperCase(),
                    converter.exchangeMoney());
        } else if (klucz.equals("empty")) {
            return new Community("API Key is required");
        } else {
            return new Community("Invalid Free API Key");

        }
    }

    @GetMapping("currencies/codes")
    public Map<String,String> getCurrencyMap(){
        return nbpService.getCurrencyList()
                .stream()
                .collect(Collectors.toMap(x -> x.getCode(), x -> x.getName()));
    }
}
