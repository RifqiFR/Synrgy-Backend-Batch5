package com.synergy.challenge6.controller;

import com.synergy.challenge6.service.InvoiceService;
import net.sf.jasperreports.engine.JRException;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Resource> getInvoice(Long seatId, Long userId, Long scheduleId) throws JRException, SQLException, IOException {
        try {
            byte[] streamResource =
                invoiceService.generateInvoice(seatId, userId, scheduleId);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new ByteArrayResource(streamResource));
        }catch (Exception e) {
            Log.debug(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
