package com.blankfactor.ra.exceptions.custom;

public class UserException extends RuntimeException {
    public UserException(String exMessage) {
        super(exMessage);
    }

    public UserException(String message, Exception e) {
        super(message);
    }
}