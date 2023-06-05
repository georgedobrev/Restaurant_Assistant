package com.blankfactor.ra.service;


import com.blankfactor.ra.dto.QrCodeDto;
import com.blankfactor.ra.model.QrCode;
import com.google.zxing.WriterException;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface QRCodeService {
    List<QrCodeDto> createQRCodeForTables(Integer restaurantId, List<Integer> tableNumbers) throws Exception;

    byte[] generateQRCodeImage(String text) throws WriterException, IOException;

    Resource createZipFile(List<QrCode> qrCodes) throws IOException;

    Integer getTableNumberByQrCodeId(Integer qrId);
}
