package com.blankfactor.ra.exceptions.custom;

public class ReportingException extends RuntimeException {
    public ReportingException(String exMessage) {
        super(exMessage);
    }

    public ReportingException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
}
