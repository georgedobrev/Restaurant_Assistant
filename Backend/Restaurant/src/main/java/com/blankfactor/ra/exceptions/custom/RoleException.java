package com.blankfactor.ra.exceptions.custom;

public class RoleException extends RuntimeException {
    public RoleException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public RoleException(String exMessage) {
        super(exMessage);
    }
}
