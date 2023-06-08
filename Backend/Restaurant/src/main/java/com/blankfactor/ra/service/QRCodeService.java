package com.blankfactor.ra.service;


import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Restaurant;
import com.google.zxing.WriterException;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface QRCodeService {
    List<AppTable> createQRCodesForTables(Restaurant restaurant, List<AppTable> appTables) throws IOException, WriterException;

    byte[] createQRCodeImage(String text) throws WriterException, IOException;

    Integer getTableNumberByQrCodeId(Integer qrId) throws Exception;

    Resource downloadQRCodes(Integer restaurantId, List<Integer> tableNumbers) throws IOException;
}