package com.blankfactor.ra.exceptions;

import com.blankfactor.ra.exceptions.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestaurantException.class)
    public ResponseEntity<ExceptionResponse> handleRestaurantException(RestaurantException ex) {
        ExceptionResponse errorResponse = new ExceptionResponse("Restaurant Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppTableException.class)
    public ResponseEntity<ExceptionResponse> handleAppTableException(AppTableException ex) {
        ExceptionResponse errorResponse = new ExceptionResponse("App Table Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QRCodeException.class)
    public ResponseEntity<ExceptionResponse> handleQRCodeException(QRCodeException ex) {
        ExceptionResponse errorResponse = new ExceptionResponse("QR Code Error", ex.getCause().getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionResponse> handleUserException(UserException ex) {
        ExceptionResponse errorResponse = new ExceptionResponse("User Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleException.class)
    public ResponseEntity<ExceptionResponse> handleRoleException(RoleException ex) {
        ExceptionResponse errorResponse = new ExceptionResponse("Role Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        ExceptionResponse errorResponse = new ExceptionResponse("Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
