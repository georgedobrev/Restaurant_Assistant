package com.blankfactor.ra.exceptions;

public class TableException extends RuntimeException {
    public TableException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public TableException(String exMessage) {
        super(exMessage);
    }
}
