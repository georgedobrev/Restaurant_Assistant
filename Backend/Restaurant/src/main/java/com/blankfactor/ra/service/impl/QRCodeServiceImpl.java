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
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@AllArgsConstructor
public class QRCodeServiceImpl implements QRCodeService {

    private final QrCodeRepository qrCodeRepository;
    private final AppConfig appConfig;
    private final AppTableRepository appTableRepository;

    public static String createHashedURL(String originalURL) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hashBytes = digest.digest(originalURL.getBytes(StandardCharsets.UTF_8));

        return Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes);
    }

    public List<AppTable> createQRCodesForTables(Restaurant restaurant, List<AppTable> appTables) throws IOException, WriterException, NoSuchAlgorithmException {
        List<QrCode> listOfQRCodes = new ArrayList<>();

        String baseUrl = appConfig.getBaseUrl() + "/qrcode";

        for (AppTable table : appTables) {
            String originalURL = restaurant.getId() + "/?table=" + table.getTableNumber();
            String hashedURL = createHashedURL(originalURL);
            byte[] qrCodeImage = createQRCodeImage(baseUrl + "/" + hashedURL);
            QrCode qrCode = new QrCode(qrCodeImage, hashedURL);
            table.setQr(qrCode);
            listOfQRCodes.add(qrCode);
        }
        qrCodeRepository.saveAll(listOfQRCodes);
        return appTables;
    }

    public byte[] createQRCodeImage(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 350, 350);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }

    public Resource downloadQRCodes(Integer restaurantId, List<Integer> tableNumbers) {
        List<AppTable> appTables = appTableRepository.findByRestaurantIdAndTableNumberIn(restaurantId, tableNumbers);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ZipOutputStream zipOut = new ZipOutputStream(baos)) {
            for (AppTable table : appTables) {

                String fileName = "qrcode_table_number_" + table.getTableNumber() + ".png";
                ZipEntry zipEntry = new ZipEntry(fileName);
                zipOut.putNextEntry(zipEntry);
                zipOut.write(table.getQr().getQrImg());
                zipOut.closeEntry();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ByteArrayResource(baos.toByteArray());
    }

    public Integer getTableNumberByQrCodeId(Integer qrId) throws Exception {
        return appTableRepository.findByQrId(qrId).orElseThrow(() -> new Exception("No table with such QR code id")).getTableNumber();
    }

    @Override
    public AppTable getTableFromQRHashUrl(String hashedUrl) throws Exception {
        QrCode qrCode = qrCodeRepository.findByHashedUrl(hashedUrl).orElseThrow(() -> new Exception("No QR code with this hashed url"));
        AppTable appTable = appTableRepository.findByQrId(qrCode.getId()).orElseThrow(() -> new Exception("No table with such QR code"));

        return appTable;
    }
}