package com.blankfactor.ra.exceptions.custom;

public class NotificationException extends RuntimeException {
    public NotificationException(String message) {
        super(message);
    }

    public NotificationException(String message, Exception e) {
        super(message);
    }
}