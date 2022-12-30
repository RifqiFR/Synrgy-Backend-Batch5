package com.synergy.challenge6.service;

import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public interface InvoiceService {
    byte[] generateInvoice(Long seatReservationId, Long userId, Long scheduleId) throws JRException, SQLException;
}
