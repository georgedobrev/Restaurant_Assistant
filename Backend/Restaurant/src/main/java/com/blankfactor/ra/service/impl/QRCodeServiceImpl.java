package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.config.AppConfig;
import com.blankfactor.ra.dto.QrCodeDto;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.QrCode;
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
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@AllArgsConstructor
public class QRCodeServiceImpl implements QRCodeService {

    private final QrCodeRepository qrCodeRepository;
    private final AppConfig appConfig;
    private final AppTableRepository appTableRepository;

    public List<QrCodeDto> createQRCodeForTables(Integer restaurantId, List<Integer> tableNumbers) throws Exception {
        List<QrCodeDto> qrCodeDtos = new ArrayList<>();
        String baseUrl = appConfig.getBaseUrl();

        for (Integer tableNumber : tableNumbers) {
            AppTable table = appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber).orElseThrow(Exception::new);

            String qrText = baseUrl + "/" + restaurantId + "/?table=" + tableNumber;

            byte[] qrCodeImage = generateQRCodeImage(qrText);
            QrCode qrCode = new QrCode(qrCodeImage);
            qrCodeRepository.save(qrCode);
            table.setQr(qrCode);
            appTableRepository.save(table);

            QrCodeDto qrCodeDto = new QrCodeDto(qrCodeImage);
            qrCodeDtos.add(qrCodeDto);
        }
        return qrCodeDtos;
    }

    public byte[] generateQRCodeImage(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 350, 350);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }

    public Resource createZipFile(List<QrCode> qrCodes) throws IOException {
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

    public Integer getTableNumberByQrCodeId(Integer qrId) {
        return appTableRepository.findByQrId(qrId).getTableNumber();
    }
}