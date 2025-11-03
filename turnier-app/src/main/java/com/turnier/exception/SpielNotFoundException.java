package com.turnier.exception;

public class SpielNotFoundException extends TurnierException {
    public SpielNotFoundException(Long id) {
        super("Spiel nicht gefunden! ID: " + id);
    }
}
