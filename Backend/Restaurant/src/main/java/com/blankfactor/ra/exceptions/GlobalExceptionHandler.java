package com.blankfactor.ra.exceptions;

import com.blankfactor.ra.exceptions.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestaurantException.class)
    public ResponseEntity<ErrorDetails> handleRestaurantException(RestaurantException ex) {
        ErrorDetails errorResponse = new ErrorDetails("Restaurant Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppTableException.class)
    public ResponseEntity<ErrorDetails> handleAppTableException(AppTableException ex) {
        ErrorDetails errorResponse = new ErrorDetails("App Table Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QRCodeException.class)
    public ResponseEntity<ErrorDetails> handleQRCodeException(QRCodeException ex) {
        ErrorDetails errorResponse = new ErrorDetails("QR Code Error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> handleUserException(UserException ex) {
        ErrorDetails errorResponse = new ErrorDetails("User Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleException.class)
    public ResponseEntity<ErrorDetails> handleRoleException(RoleException ex) {
        ErrorDetails errorResponse = new ErrorDetails("Role Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception ex) {
        ErrorDetails errorResponse = new ErrorDetails("Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
