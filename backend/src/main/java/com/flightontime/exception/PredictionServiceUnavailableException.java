package com.flightontime.exception;

public class PredictionServiceUnavailableException extends RuntimeException {
    public PredictionServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
    public PredictionServiceUnavailableException(String message) {
        super(message);
    }
}