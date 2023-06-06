package com.blankfactor.ra.exceptions;

public class RoleException extends RuntimeException {
    public RoleException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public RoleException(String exMessage) {
        super(exMessage);
    }
}
