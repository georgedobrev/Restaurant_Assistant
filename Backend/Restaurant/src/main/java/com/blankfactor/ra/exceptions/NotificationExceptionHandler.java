package com.blankfactor.ra.exceptions;

import com.blankfactor.ra.exceptions.custom.NotificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class NotificationExceptionHandler {

    @ExceptionHandler(NotificationException.class)
    public ResponseEntity<Object> handleNotificationRequestException(NotificationException n) {
        //1. create payload
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
       NotificationException notificationException = new NotificationException(
                n.getMessage(),
                n,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        //2. return response entity
        return new ResponseEntity<>(notificationException, badRequest);
    }
}
