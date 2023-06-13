package com.blankfactor.ra.controller;


import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.AppUser;
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

    @PostMapping("/{hashedUrl}")
    public ResponseEntity<AppTable> getTableFromQRHashUrl(@PathVariable("hashedUrl") String hashedUrl, @RequestBody AppUser user) {
        // how to get the User object after the authentication?
        // how we go assign the Waiter object to the UserTable record
        // Do we need restaurant sections so we can find the waiter by section?
        // can we assign all waiters for all tables?
        // todo
        AppTable response = qrCodeService.getTableFromQRHashUrl(hashedUrl, user, user);

        return ResponseEntity.ok(response);
    }
}

