package com.blankfactor.ra.controller;


import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.service.QRCodeService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/qrcode")
@AllArgsConstructor
public class QRCodeController {

    private final QRCodeService qrCodeService;

    @PostMapping("/download/{restaurantId}")
    public ResponseEntity<Resource> downloadQRCodes(@PathVariable("restaurantId") Integer restaurantId,
                                                    @RequestParam("ids") List<Integer> tableNumbers) throws Exception {

        Resource zipFileResource = qrCodeService.downloadQRCodes(restaurantId, tableNumbers);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        headers.setContentDisposition(ContentDisposition.attachment().filename("qrcodes.zip").build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(zipFileResource);
    }

    @GetMapping("/{hashedURL}")
    public ResponseEntity<AppTable> getQRCodeInfo(@PathVariable("hashedURL") String hashedURL) throws Exception {
        AppTable response = qrCodeService.getTableFromQRHashURL(hashedURL);

        return ResponseEntity.ok(response);
    }
}
