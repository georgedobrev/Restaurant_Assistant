package com.blankfactor.ra.exceptions.custom;

public class UserRoleException extends RuntimeException {
    public UserRoleException(String exMessage) {
        super(exMessage);
    }
}