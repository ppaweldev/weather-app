package com.example.weatherapp.exception;

public class CityNotFoundException extends RuntimeException {
    private static final String MESSAGE = "There is no data for city: %s";

    public CityNotFoundException(String city) {
        super(MESSAGE.formatted(city));
    }
}
