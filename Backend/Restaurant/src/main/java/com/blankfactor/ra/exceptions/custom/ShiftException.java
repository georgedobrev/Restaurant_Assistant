package com.blankfactor.ra.exceptions.custom;

public class ShiftException extends RuntimeException {
    public ShiftException(String message) {
        super(message);
    }

    public ShiftException(String message, Exception e) {
        super(message);
    }
}