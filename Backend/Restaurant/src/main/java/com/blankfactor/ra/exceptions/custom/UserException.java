package com.blankfactor.ra.exceptions.custom;

public class UserException extends RuntimeException {
    public UserException(String exMessage) {
        super(exMessage);
    }

    public UserException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
}
