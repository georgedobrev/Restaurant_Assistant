package com.blankfactor.ra.exceptions.custom;

public class RestaurantException extends RuntimeException {
    public RestaurantException(String exMessage) {
        super(exMessage);
    }
}