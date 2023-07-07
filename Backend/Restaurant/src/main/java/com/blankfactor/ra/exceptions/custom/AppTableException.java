package com.blankfactor.ra.exceptions.custom;

public class AppTableException extends RuntimeException {
    public AppTableException(String message) {
        super(message);
    }

    public AppTableException(String message, Exception e) {
        super(message);
    }
}