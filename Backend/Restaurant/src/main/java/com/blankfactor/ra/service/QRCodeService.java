package com.blankfactor.ra.service;

import com.blankfactor.ra.config.AppConfig;
import com.blankfactor.ra.entity.QRCode;
import com.blankfactor.ra.entity.Table;
import com.blankfactor.ra.repository.QrCodeRepository;
import com.blankfactor.ra.repository.TableRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class QRCodeService {
    private final QrCodeRepository qrCodeRepository;
    private final AppConfig appConfig;
    private final TableRepository tableRepository;
    @Autowired
    public QRCodeService(QrCodeRepository qrCodeRepository, AppConfig appConfig, TableRepository tableRepository) {
        this.qrCodeRepository = qrCodeRepository;
        this.appConfig = appConfig;
        this.tableRepository = tableRepository;
    }

    private byte[] generateQRCodeImage(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 350, 350);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }

    public List<QRCode> generateQRCodeForTables(List<Integer> tableIds) {
        List<QRCode> qrCodes = new ArrayList<>();
        String baseUrl = appConfig.getBaseUrl();

        for (Integer tableId : tableIds) {
            String qrText = baseUrl + "/?id=" + tableId;

            try {
                byte[] qrCodeImage = generateQRCodeImage(qrText);
                QRCode qrCode = new QRCode(qrCodeImage);
                qrCodeRepository.save(qrCode);

                Optional<Table> optionalTable = tableRepository.findById(tableId);
                if (optionalTable.isPresent()) {
                    Table table = optionalTable.get();
                    table.setQrCode(qrCode);
                    tableRepository.save(table);
                }

                qrCodes.add(qrCode);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return qrCodes;
    }

    public List<QRCode> getQRCodesByIds(List<Integer> ids) {
        return qrCodeRepository.findAllById(ids);
    }

    public Integer getTableIdByQrCodeId(Integer qrId) {
        return qrCodeRepository.findTableIdByQrCodeId(qrId);
    }

    public List<QRCode> getQRCodesByTableIds(List<Integer> tableIds) {
        List<QRCode> qrCodes = new ArrayList<>();
        for (Integer tableId : tableIds) {
            Optional<Table> optionalTable = tableRepository.findById(tableId);
            if (optionalTable.isPresent()) {
                QRCode qrCode = optionalTable.get().getQrCode();
                if (qrCode != null) {
                    qrCodes.add(qrCode);
                }
            }
        }
        return qrCodes;
    }


    public Resource createZipFile(List<QRCode> qrCodes) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ZipOutputStream zipOut = new ZipOutputStream(baos)) {
            for (QRCode qrCode : qrCodes) {

                Integer qrCodeId = qrCode.getId();
                Integer tableId = getTableIdByQrCodeId(qrCodeId);

                String fileName = "qrcode_table_id" + tableId + ".png";
                ZipEntry zipEntry = new ZipEntry(fileName);
                zipOut.putNextEntry(zipEntry);
                zipOut.write(qrCode.getQrImg());
                zipOut.closeEntry();
            }
        }
        return new ByteArrayResource(baos.toByteArray());
    }
}