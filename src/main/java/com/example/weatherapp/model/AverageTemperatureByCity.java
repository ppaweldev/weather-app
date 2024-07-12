package com.example.weatherapp.model;

import java.math.BigDecimal;

public record AverageTemperatureByCity(
        String city,
        int year,
        BigDecimal averageTemp
) {
}
