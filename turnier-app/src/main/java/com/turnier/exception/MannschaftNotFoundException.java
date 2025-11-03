package com.turnier.exception;

public class MannschaftNotFoundException extends TurnierException {
    public MannschaftNotFoundException(Long id) {
        super("Mannschaft nicht gefunden! ID: " + id);
    }
}
