package com.example.weatherapp.api.controller;


import com.example.weatherapp.api.dto.ExceptionMessage;
import com.example.weatherapp.exception.CityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@ResponseBody
class GlobalExceptionHandler {

    @ExceptionHandler(CityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ExceptionMessage handleCityNotFoundException(CityNotFoundException ex) {
        return new ExceptionMessage(ex.getMessage());
    }

}
