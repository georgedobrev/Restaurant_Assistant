package com.blankfactor.ra.exceptions.custom;

public class MergedTableException extends RuntimeException {
    public MergedTableException(String exMessage) {
        super(exMessage);
    }

    public MergedTableException(String message, Exception e) {
        super(message);
    }
}