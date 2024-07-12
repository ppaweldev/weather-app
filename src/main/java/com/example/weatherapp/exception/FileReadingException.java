package com.example.weatherapp.exception;

public class FileReadingException extends RuntimeException {
    private static final String MESSAGE = "Exception occurred during reading file: %s";

    public FileReadingException(String message) {
        super(MESSAGE.formatted(message));
    }
}
