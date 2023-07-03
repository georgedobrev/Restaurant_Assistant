package com.blankfactor.ra.exceptions.custom;

public class ShiftException extends Throwable {
    public ShiftException(String message) {
        super(message);
    }

    public ShiftException(String message, Exception e) {
        super(message);
    }
}