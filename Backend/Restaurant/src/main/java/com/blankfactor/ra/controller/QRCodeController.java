package com.blankfactor.ra.controller;


import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.QrCodeRepository;
import com.blankfactor.ra.service.QRCodeService;
import com.blankfactor.ra.service.impl.QRCodeServiceImpl;
import com.blankfactor.ra.model.QrCode;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    private final QRCodeService qrCodeService;
    private final AppTableRepository appTableRepository;
    private final QrCodeRepository qrCodeRepository;

    @PostMapping("/generate")
    public ResponseEntity<List<QrCode>> generateQRCodeForTables(@RequestParam("restaurantId") Integer restaurantId, @RequestBody List<Integer> tableIds) {
        List<QrCode> qrCodes = qrCodeService.generateQRCodeForTables(restaurantId, tableIds);

        if (!qrCodes.isEmpty()) {
            return ResponseEntity.ok(qrCodes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/download")
    public ResponseEntity<Resource> downloadQRCodes(@PathVariable("restaurantId") Integer restaurantId,
                                                    @RequestParam("ids") List<Integer> tableNumbers) throws IOException {

        List<Integer> qrIds = appTableRepository.findQRIdByRestaurantIdAndTableNumbers(restaurantId, tableNumbers);
        List<QrCode> qrCodes = qrCodeRepository.findQRCodesByIdIn(qrIds);
        Resource zipFileResource = qrCodeService.createZipFile(qrCodes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        headers.setContentDisposition(ContentDisposition.attachment().filename("qrcodes.zip").build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(zipFileResource);
    }
}
