package com.rsjavasolution.currencyConverter.controller;

import com.rsjavasolution.currencyConverter.dto.mapper.LogMapper;
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
    private LogMapper logMapper;

    @Autowired
    LogRepository logRepository;

    @GetMapping("currencies")
    public Object getAllCurrencyList(@RequestParam(defaultValue = "enterKey") String apiKey) {

        Object object = null;
        if (apiKey.equals(key)) {
            log = createLog("api/currencies", "OK", apiKey);
            object = nbpService.getCurrencyList();
        } else if (apiKey.equals("enterKey")) {
            log = createLog("api/currencies", "NOT_FOUND", "");
            object = new Community("API Key is required");
        } else {
            log = createLog("api/currencies", "NOT_FOUND", apiKey);
            // logRepository.save(log);
            object = new Community("Invalid Free API Key");
        }

        logMapper.mapToLogtDto(logRepository.save(log));
        return object;
    }

    @GetMapping("currencies/convert")
    public Object exchangeMoney(
            @RequestParam(defaultValue = "enterKey") String apiKey,
            String from, String to, double amount) {
        Object object = null;
        Converter converter = new Converter(from, to, amount);


        if (apiKey.equals(key)) {
            log = createLog("api/currencies/convert", "OK",
                    apiKey + " , " + from + " , " + to + " , " + amount);
            object = new Exchanger(from.toUpperCase(),
                    to.toUpperCase(),
                    converter.exchangeMoney());
        } else if (apiKey.equals("enterKey")) {
            log = createLog("api/currencies/convert", "UNAUTHORIZED",
                    "" + " , " + from + " , " + to + " , " + amount);
            object = new Community("API Key is required");
        } else {
            log = createLog("api/currencies/convert", "UNAUTHORIZED",
                    apiKey + " , " + from + " , " + to + " , " + amount);
            object = new Community("Invalid Free API Key");
        }
        logMapper.mapToLogtDto(logRepository.save(log));
        return object;
    }

    @GetMapping("currencies/codes")
    public Object getAvailableCurrencyList(@RequestParam(defaultValue = "enterKey") String apiKey) {

        Object object = null;
        if (apiKey.equals(key)) {
            log = createLog("api/currencies/codes", "OK", apiKey);
            object = nbpService.getAvailableCurrencyList();
        } else if (apiKey.equals("enterKey")) {
            log = createLog("api/currencies/codes", "UNAUTHORIZED", "");
            object = new Community("API Key is required");
        } else {
            log = createLog("api/currencies/codes", "UNAUTHORIZED", apiKey);
            object = new Community("Invalid Free API Key");
        }
        logMapper.mapToLogtDto(logRepository.save(log));
        return object;
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