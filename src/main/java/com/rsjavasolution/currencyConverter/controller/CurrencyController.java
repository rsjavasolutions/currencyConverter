package com.rsjavasolution.currencyConverter.controller;

import com.rsjavasolution.currencyConverter.dto.mapper.LogMapper;
import com.rsjavasolution.currencyConverter.exception.InvalidKeyException;
import com.rsjavasolution.currencyConverter.exception.KeyNotFoundException;
import com.rsjavasolution.currencyConverter.model.*;
import com.rsjavasolution.currencyConverter.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    public List<Currency> getAllCurrencyList(@RequestParam(defaultValue = "enterKey") String apiKey) {

        List<Currency> currencyList;
        if (apiKey.equals(key)) {
            log = createLog("api/currencies", "OK", apiKey);
            currencyList = nbpService.getCurrencyList();
        } else if (apiKey.equals("enterKey")) {
            log = createLog("api/currencies", "UNAUTHORIZED", "");
            throw new KeyNotFoundException();
        } else {
            log = createLog("api/currencies", "UNAUTHORIZED", apiKey);
            throw new InvalidKeyException(apiKey);
        }
        logMapper.mapToLogtDto(logRepository.save(log));
        return currencyList;
    }

    @GetMapping("currencies/convert")
    public Exchanger exchangeMoney(
            @RequestParam(defaultValue = "enterKey") String apiKey,
            String from, String to, double amount) {
        Exchanger exchanger = null;
        Converter converter = new Converter(from, to, amount);

        if (apiKey.equals(key)) {
            log = createLog("api/currencies/convert", "OK",
                    apiKey + " , " + from + " , " + to + " , " + amount);
            exchanger = new Exchanger(from.toUpperCase(),
                    to.toUpperCase(),
                    converter.exchangeMoney());
        } else if (apiKey.equals("enterKey")) {
            log = createLog("api/currencies/convert", "UNAUTHORIZED",
                    "" + " , " + from + " , " + to + " , " + amount);
            throw new KeyNotFoundException();
        } else {
            log = createLog("api/currencies/convert", "UNAUTHORIZED",
                    apiKey + " , " + from + " , " + to + " , " + amount);
            throw new InvalidKeyException(apiKey);
        }
        logMapper.mapToLogtDto(logRepository.save(log));
        return exchanger;
    }

    @GetMapping("currencies/codes")
    public List<AvailableCurrency> getAvailableCurrencyList(@RequestParam(defaultValue = "enterKey") String apiKey) {
        List<AvailableCurrency> currencies = null;
        if (apiKey.equals(key)) {
            log = createLog("api/currencies/codes", "OK", apiKey);
            currencies = nbpService.getAvailableCurrencyList();
        } else if (apiKey.equals("enterKey")) {
            log = createLog("api/currencies/codes", "UNAUTHORIZED", "");
            throw new KeyNotFoundException();
        } else {
            log = createLog("api/currencies/codes", "UNAUTHORIZED", apiKey);
            throw new InvalidKeyException(apiKey);
        }
        logMapper.mapToLogtDto(logRepository.save(log));
        return currencies;
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
