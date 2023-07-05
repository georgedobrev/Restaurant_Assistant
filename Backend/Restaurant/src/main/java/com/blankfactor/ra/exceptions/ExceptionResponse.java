package com.blankfactor.ra.exceptions;

import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ExceptionResponse {
    String date;
    String message;
    String details;

    public ExceptionResponse(String message, String details) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = formatter.format(new Date());
        this.message = message;
        this.details = details;
    }
}