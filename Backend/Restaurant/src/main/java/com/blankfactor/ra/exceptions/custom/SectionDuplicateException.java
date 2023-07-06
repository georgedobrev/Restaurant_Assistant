package com.blankfactor.ra.exceptions.custom;

public class SectionDuplicateException extends RuntimeException {
    public SectionDuplicateException(String exMessage) {
        super(exMessage);
    }

    public SectionDuplicateException(String message, Exception e) {
        super(message);
    }
}