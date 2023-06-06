package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.config.AppConfig;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.QrCode;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.QrCodeRepository;
import com.blankfactor.ra.service.QRCodeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@AllArgsConstructor
public class QRCodeServiceImpl implements QRCodeService {

    private final QrCodeRepository qrCodeRepository;
    private final AppConfig appConfig;
    private final AppTableRepository appTableRepository;

    public AppTable createQRCodeForTables(Restaurant restaurant, AppTable appTable) throws IOException, WriterException {
        String baseUrl = appConfig.getBaseUrl();
        String qrText = baseUrl + "/" + restaurant.getId() + "/?table=" + appTable.getTableNumber();

        byte[] qrCodeImage = createQRCodeImage(qrText);
        QrCode qrCode = new QrCode(qrCodeImage);
        qrCodeRepository.save(qrCode);
        appTable.setQr(qrCode);
        return appTable;
    }

    public byte[] createQRCodeImage(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 350, 350);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }

    public Resource createZipFile(List<QrCode> qrCodes) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ZipOutputStream zipOut = new ZipOutputStream(baos)) {
            for (QrCode qrCode : qrCodes) {

                Integer qrCodeId = qrCode.getId();
                Integer tableNumber = getTableNumberByQrCodeId(qrCodeId);

                String fileName = "qrcode_table_id_" + tableNumber + ".png";
                ZipEntry zipEntry = new ZipEntry(fileName);
                zipOut.putNextEntry(zipEntry);
                zipOut.write(qrCode.getQrImg());
                zipOut.closeEntry();
            }
        }
        return new ByteArrayResource(baos.toByteArray());
    }

    public Integer getTableNumberByQrCodeId(Integer qrId) throws Exception {
        return appTableRepository.findByQrId(qrId).orElseThrow(Exception::new).getTableNumber();
    }
}