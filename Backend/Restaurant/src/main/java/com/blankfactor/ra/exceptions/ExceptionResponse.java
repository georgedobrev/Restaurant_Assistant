package com.blankfactor.ra.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
