package com.rsjavasolution.currencyConverter.controller;

import com.rsjavasolution.currencyConverter.model.*;
import com.rsjavasolution.currencyConverter.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequestMapping("/api/")
@RestController
public class CurrencyController {

    private Log log;
    private String key = Constant.API_KEY;
    private NbpService nbpService = new NbpService();

    @Autowired
    LogRepository logRepository;

    @GetMapping("currencies")
    public Object getList(@RequestParam(defaultValue = "enterKey") String apiKey) {

            if (apiKey.equals(key)) {
                log = createLog("api/currencies", "OK", apiKey);
                logRepository.save(log);
                return nbpService.getCurrencyList();
            } else if (apiKey.equals("enterKey")) {
                log = createLog("api/currencies","NOT_FOUND","");
                logRepository.save(log);
                return new Community("API Key is required");
            } else {
                log = createLog("api/currencies","NOT_FOUND",apiKey);
                logRepository.save(log);
                return new Community("Invalid Free API Key");
            }
        }

    @GetMapping("currencies/convert")
    public Object exchangeMoney(
            @RequestParam(defaultValue = "enterKey") String apiKey,
            String from, String to, double amount) {
        Converter converter = new Converter(from, to, amount);

        if (apiKey.equals(key)) {
            log = createLog("api/convert", "OK",
                    apiKey + " , " + from + " , " + to + " , " + amount);
            logRepository.save(log);
            return new Exchanger(from.toUpperCase(),
                    to.toUpperCase(),
                    converter.exchangeMoney());
        } else if (apiKey.equals("enterKey")) {
            log = createLog("api/convert", "UNAUTHORIZED",
                    ""+ " , " + from + " , " + to + " , " + amount);
            return new Community("API Key is required");
        } else {
            log = createLog("api/convert", "UNAUTHORIZED",
                    apiKey + " , " + from + " , " + to + " , " + amount);
            logRepository.save(log);
            return new Community("Invalid Free API Key");
        }
    }

    @GetMapping("currencies/codes")
    public Object getAvailableCurrencyList(@RequestParam(defaultValue = "enterKey") String apiKey) {
        if (apiKey.equals(key)) {
            log = createLog("api/currencies/codes", "OK", apiKey);
            logRepository.save(log);
            return nbpService.getAvailableCurrencyList();
        } else if (apiKey.equals("enterKey")) {
            log = createLog("api/currencies/codes","UNAUTHORIZED","");
            logRepository.save(log);
            return new Community("API Key is required");
        } else {
            log = createLog("api/currencies/codes","UNAUTHORIZED",apiKey);
            logRepository.save(log);
            return new Community("Invalid Free API Key");
        }
    }

    private Log createLog(String url, String status, String param) {
        log = new Log();
        log.setUrl(url);
        log.setHttpMethod("GET");
        log.setDateAndHour(localDate());
        log.setHttpStatus(status);
        log.setParameters(param);
        return log;
    }

    public String localDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return localDateTime.format(dataFormat);
    }
}