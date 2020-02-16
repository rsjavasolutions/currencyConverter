package com.rsjavasolution.currencyConverter.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class LogDto {

    private Long id;
    private String url;
    private String HttpMethod;
    private String dateAndHour;
    private String HttpStatus;
    private String parameters;
}
