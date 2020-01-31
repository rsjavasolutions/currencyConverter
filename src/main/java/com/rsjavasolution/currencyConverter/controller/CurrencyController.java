package com.rsjavasolution.currencyConverter.controller;
import com.rsjavasolution.currencyConverter.model.Converter;
import com.rsjavasolution.currencyConverter.model.Currency;
import com.rsjavasolution.currencyConverter.model.Exchanger;
import com.rsjavasolution.currencyConverter.model.NbpService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/api/")
@RestController
public class CurrencyController {

    private NbpService nbpService = new NbpService();

    @GetMapping("currencies")
    public List<Currency> getList() {

        return nbpService.getCurrencyList();
    }

    @GetMapping("convert")
    public Exchanger getExchange(@RequestParam String from, String to, double amount) {
        Converter converter = new Converter(from, to, amount);
        return new Exchanger(from.toUpperCase(),
                to.toUpperCase(),
                converter.exchangeMoney());
    }

    @GetMapping("currencies/codes")
    public Map<String,String> getCurrencyMap(){
        return nbpService.getCurrencyList()
                .stream()
                .collect(Collectors.toMap(x -> x.getCode(), x -> x.getName()));
    }
}
