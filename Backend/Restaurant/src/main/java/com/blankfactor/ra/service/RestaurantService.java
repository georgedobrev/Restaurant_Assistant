package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Restaurant;
import com.google.zxing.qrcode.encoder.QRCode;

import java.util.List;

public interface RestaurantService {

    Restaurant save(RestaurantDto restaurantDto);

}