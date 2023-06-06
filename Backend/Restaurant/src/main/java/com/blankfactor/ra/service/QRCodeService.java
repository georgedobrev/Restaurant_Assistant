package com.blankfactor.ra.service;


import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.QrCode;
import com.blankfactor.ra.model.Restaurant;
import com.google.zxing.WriterException;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface QRCodeService {
    AppTable createQRCodeForTables(Restaurant restaurant, AppTable appTable) throws IOException, WriterException;

    byte[] createQRCodeImage(String text) throws WriterException, IOException;

    Resource createZipFile(List<QrCode> qrCodes) throws Exception;

    Integer getTableNumberByQrCodeId(Integer qrId) throws Exception;
}
