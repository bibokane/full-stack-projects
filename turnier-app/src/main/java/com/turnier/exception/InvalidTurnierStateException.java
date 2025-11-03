package com.turnier.exception;

public class InvalidTurnierStateException extends TurnierException {
    public InvalidTurnierStateException(String message) {
        super(message);
    }
}
