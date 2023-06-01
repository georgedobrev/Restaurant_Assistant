package com.blankfactor.ra.exceptions;

public class RestaurantAssistantException extends RuntimeException {
    public RestaurantAssistantException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public RestaurantAssistantException(String exMessage) {
        super(exMessage);
    }
}