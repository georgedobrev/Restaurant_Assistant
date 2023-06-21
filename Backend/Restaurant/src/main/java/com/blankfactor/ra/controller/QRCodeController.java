package com.blankfactor.ra.controller;


import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.service.QRCodeService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Download QR code/s for specific table/s")
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

    @PostMapping("/{hashedUrl}")
    @Operation(summary = "Set user as seated after the QR code is scanned")
    public ResponseEntity<AppTable> setUserAsSeatedAtTableFromQRHashedUrl(@PathVariable("hashedUrl") String hashedUrl, @RequestBody AppUser user) {
        AppTable response = qrCodeService.setUserAsSeatedAtTableFromQRHashedUrl(hashedUrl, user);

        return ResponseEntity.ok(response);
    }
}