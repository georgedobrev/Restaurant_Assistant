package com.blankfactor.ra.exceptions;

import com.blankfactor.ra.exceptions.custom.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

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

    @ExceptionHandler(UserRoleException.class)
    public ResponseEntity<ExceptionResponse> handleUserRoleException(UserRoleException ex) {
        ExceptionResponse errorResponse = new ExceptionResponse("Role Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VirtualTableException.class)
    public ResponseEntity<ExceptionResponse> handleVirtualTableException(VirtualTableException ex) {
        ExceptionResponse errorResponse = new ExceptionResponse("Virtual Table Exception", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SectionDuplicateException.class)
    public ResponseEntity<ExceptionResponse> handleSectionDuplicateException(SectionDuplicateException ex) {
        ExceptionResponse errorResponse = new ExceptionResponse("Section duplicate exception", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        ExceptionResponse errorResponse = new ExceptionResponse("Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotificationException.class)
    public ResponseEntity<ExceptionResponse> handleNotificationException(NotificationException ex) {
        ExceptionResponse errorResponse = new ExceptionResponse("Notification Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShiftException.class)
    public ResponseEntity<ExceptionResponse> handleShiftException(ShiftException ex) {
        ExceptionResponse errorResponse = new ExceptionResponse("Shift Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        ExceptionResponse errorResponse = new ExceptionResponse("Method Argument Not Valid", errors.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TenantException.class)
    public ResponseEntity<ExceptionResponse> handleTenantException(TenantException ex) {
        ExceptionResponse errorResponse = new ExceptionResponse("Tenant Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SysadminException.class)
    public ResponseEntity<ExceptionResponse> handleSysadminException(SysadminException ex) {
        ExceptionResponse errorResponse = new ExceptionResponse("Sysadmin Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
