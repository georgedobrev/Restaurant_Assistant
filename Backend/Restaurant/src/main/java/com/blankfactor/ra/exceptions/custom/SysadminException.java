package com.blankfactor.ra.exceptions.custom;

public class SysadminException extends Exception{
    public SysadminException(String exMessage) {
        super(exMessage);
    }
    public SysadminException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
}
