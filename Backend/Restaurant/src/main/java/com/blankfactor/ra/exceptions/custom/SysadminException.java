package com.blankfactor.ra.exceptions.custom;

public class SysadminException extends RuntimeException {
    public SysadminException(String exMessage) {
        super(exMessage);
    }

    public SysadminException(String message, Exception e) {
        super(message);
    }
}