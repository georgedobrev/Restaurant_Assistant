package com.blankfactor.ra.exceptions;

public class UserException extends RuntimeException {
    public UserException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public UserException(String exMessage) {
        super(exMessage);
    }
}
