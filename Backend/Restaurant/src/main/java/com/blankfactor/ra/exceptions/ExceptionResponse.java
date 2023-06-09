package com.blankfactor.ra.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse {
    Date date;
    String message;
    String details;

    public ExceptionResponse(String message, String details) {
        this.date = new Date();
        this.message = message;
        this.details = details;
    }
}
