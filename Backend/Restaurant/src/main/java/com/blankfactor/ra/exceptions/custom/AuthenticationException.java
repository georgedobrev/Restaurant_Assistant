package com.blankfactor.ra.exceptions.custom;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}