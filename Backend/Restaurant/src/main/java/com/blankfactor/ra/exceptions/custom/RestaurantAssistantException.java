package com.blankfactor.ra.exceptions.custom;

public class RestaurantAssistantException extends RuntimeException {
    public RestaurantAssistantException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public RestaurantAssistantException(String exMessage) {
        super(exMessage);
    }
}