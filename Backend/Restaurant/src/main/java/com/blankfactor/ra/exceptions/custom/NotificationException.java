package com.blankfactor.ra.exceptions.custom;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class NotificationException extends RuntimeException{
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime time;

    public NotificationException(String message,
                                 Throwable throwable,
                                 HttpStatus httpStatus, ZonedDateTime time) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.time = time;
    }

    public NotificationException(String message, ZonedDateTime time) {
        super(message);
        this.time = time;
    }

    public NotificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
