package com.blankfactor.ra.exceptions.custom;

public class TenantException extends RuntimeException {
    public TenantException(String message) {
        super(message);
    }

    public TenantException(String message, Exception e) {
        super(message);
    }
}
