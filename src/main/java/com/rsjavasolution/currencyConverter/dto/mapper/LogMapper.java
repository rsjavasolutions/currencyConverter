package com.rsjavasolution.currencyConverter.dto.mapper;

import com.rsjavasolution.currencyConverter.dto.LogDto;
import com.rsjavasolution.currencyConverter.model.Log;
import org.springframework.stereotype.Component;

@Component
public class LogMapper {

    private Long id;
    private String url;
    private String HttpMethod;
    private String dateAndHour;
    private String HttpStatus;
    private String parameters;

    public LogDto mapToLogtDto(Log log){
        LogDto logDto = new LogDto();

        logDto.setId(log.getId());
        logDto.setUrl(log.getUrl());
        logDto.setHttpMethod(log.getHttpMethod());
        logDto.setDateAndHour(log.getDateAndHour());
        logDto.setHttpStatus(log.getHttpStatus());

        return logDto;
    }
}
