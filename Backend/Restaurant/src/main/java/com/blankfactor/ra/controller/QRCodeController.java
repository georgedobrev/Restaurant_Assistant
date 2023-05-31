package com.blankfactor.ra.controller;

import com.blankfactor.ra.entity.QRCode;
import com.blankfactor.ra.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {
    private final QRCodeService qrCodeService;

    @Autowired
    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @PostMapping("/generate")
    public ResponseEntity<List<QRCode>> generateQRCodeForTables(@RequestBody List<Integer> tableIds) {
        List<QRCode> qrCodes = qrCodeService.generateQRCodeForTables(tableIds);

        if (!qrCodes.isEmpty()) {
            return ResponseEntity.ok(qrCodes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadQRCodes(@RequestParam("ids") List<Integer> ids) throws IOException {
        List<QRCode> qrCodes = qrCodeService.getQRCodesByTableIds(ids);
        Resource zipFileResource = qrCodeService.createZipFile(qrCodes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename("qrcodes.zip").build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(zipFileResource);
    }
}
