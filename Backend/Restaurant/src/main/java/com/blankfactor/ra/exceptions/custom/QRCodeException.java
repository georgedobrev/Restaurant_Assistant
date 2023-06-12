package com.blankfactor.ra.exceptions.custom;

public class QRCodeException extends RuntimeException {
    public QRCodeException(String message) {
        super(message);
    }

    public QRCodeException(String message, Exception e) {
        super(message);
    }
}
