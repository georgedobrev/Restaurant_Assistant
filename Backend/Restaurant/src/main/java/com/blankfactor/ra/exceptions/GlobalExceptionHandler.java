package com.blankfactor.ra.exceptions;

import com.blankfactor.ra.exceptions.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestaurantException.class)
    public ResponseEntity<RAErrorResponse> handleRestaurantNotFoundException(RestaurantException ex) {
        RAErrorResponse errorResponse = new RAErrorResponse("Restaurant Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppTableNotFoundException.class)
    public ResponseEntity<RAErrorResponse> handleAppTableNotFoundException(AppTableNotFoundException ex) {
        RAErrorResponse errorResponse = new RAErrorResponse("App Table Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QRCodeException.class)
    public ResponseEntity<RAErrorResponse> handleQRCodeException(QRCodeException ex) {
        RAErrorResponse errorResponse = new RAErrorResponse("QR Code Error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<RAErrorResponse> handleUserException(UserException ex) {
        RAErrorResponse errorResponse = new RAErrorResponse("User Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleException.class)
    public ResponseEntity<RAErrorResponse> handleRoleException(RoleException ex) {
        RAErrorResponse errorResponse = new RAErrorResponse("Role Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RAErrorResponse> handleException(Exception ex) {
        RAErrorResponse errorResponse = new RAErrorResponse("Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
