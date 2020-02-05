package com.rsjavasolution.currencyConverter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity

@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    @Column(name = "HTTP_method")
    private String HttpMethod;
    @Column(name = "RequestTime")
    private String dateAndHour;
    @Column(name = "HTTP_status")
    private String HttpStatus;
    private String parameters;
}

