package com.blankfactor.ra.exceptions.custom;

public class AppTableNotFoundException extends RuntimeException {
    public AppTableNotFoundException(String message) {
        super(message);
    }
}
