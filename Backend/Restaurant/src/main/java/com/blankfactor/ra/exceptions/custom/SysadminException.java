package com.blankfactor.ra.exceptions.custom;

public class SysadminException extends RuntimeException {
    public SysadminException(String exMessage) {
        super(exMessage);
    }

    public SysadminException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
}
