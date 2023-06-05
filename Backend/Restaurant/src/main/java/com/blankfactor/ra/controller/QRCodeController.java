package com.blankfactor.ra.controller;


import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.QrCode;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.QrCodeRepository;
import com.blankfactor.ra.service.QRCodeService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/{restaurantId}/qrcode")
@AllArgsConstructor
public class QRCodeController {

    private final QRCodeService qrCodeService;
    private final AppTableRepository appTableRepository;
    private final QrCodeRepository qrCodeRepository;

    @PostMapping("/create")
    public ResponseEntity<List<QrCode>> createQRCodeForTables(@RequestParam("restaurantId") Integer restaurantId, @RequestBody List<Integer> tableIds) throws Exception {
        List<QrCode> qrCodes = qrCodeService.createQRCodeForTables(restaurantId, tableIds);

        return ResponseEntity.status(HttpStatus.CREATED).body(qrCodes);
    }

    @PostMapping("/download")
    public ResponseEntity<Resource> downloadQRCodes(@PathVariable("restaurantId") Integer restaurantId,
                                                    @RequestParam("ids") List<Integer> tableNumbers) throws Exception {
//        Optional<List<AppTable>> qrIds1 = new ArrayList<>();
//        for (Integer tableNumber : tableNumbers) {
//            qrIds1.add(appTableRepository.findByRestaurantId(restaurantId));
//        }
        Optional<List<Integer>> qrIds = appTableRepository.findQRIdByRestaurantIdAndTableNumbers(restaurantId, tableNumbers);
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
