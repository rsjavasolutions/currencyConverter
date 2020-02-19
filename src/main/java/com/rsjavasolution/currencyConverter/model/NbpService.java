package com.rsjavasolution.currencyConverter.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NbpService {

    private String url;
    private List<Currency> currencyList;
    private List<AvailableCurrency> availableCurrencyList;

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public List<AvailableCurrency> getAvailableCurrencyList() {
        return availableCurrencyList;
    }



    public NbpService() {
        url = "http://api.nbp.pl/api/exchangerates/tables/A/?format=json";
        currencyList = new ArrayList<>();
        availableCurrencyList = new ArrayList<>();

        try {
            URL link = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            JSONParser parser = new JSONParser();
            Object currencies = parser.parse(bufferedReader);

            JSONArray array = (JSONArray) currencies;
            JSONObject jsonObject = (JSONObject) array.get(0);
            JSONArray currencyArray = (JSONArray) jsonObject.get("rates");

            for (int i = 0; i < currencyArray.size(); i++) {
                currencyList.add(new Currency
                                ((String) ((JSONObject) currencyArray.get(i)).get("currency"),
                                (String) ((JSONObject) currencyArray.get(i)).get("code"),
                                (Double) ((JSONObject) currencyArray.get(i)).get("mid")));

                availableCurrencyList.add(new AvailableCurrency
                                ((String) ((JSONObject) currencyArray.get(i)).get("code"),
                                (String) ((JSONObject) currencyArray.get(i)).get("currency")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



