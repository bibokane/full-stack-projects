package com.turnier.exception;

public class TurnierException extends RuntimeException {
    public TurnierException(String message) {
        super(message);
    }
    
    public TurnierException(String message, Throwable cause) {
        super(message, cause);
    }
}
