package com.blankfactor.ra.exceptions.custom;

public class SectionException extends RuntimeException {
    public SectionException(String exMessage) {
        super(exMessage);
    }

    public SectionException(String message, Exception e) {
        super(message);
    }
}