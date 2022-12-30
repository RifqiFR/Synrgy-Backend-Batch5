package com.synergy.challenge6.service;

import com.synergy.challenge6.model.Schedule;
import com.synergy.challenge6.model.SeatReservation;
import com.synergy.challenge6.model.id.SeatReservationId;
import com.synergy.challenge6.repository.ScheduleRepository;
import com.synergy.challenge6.repository.SeatReservationRepository;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private SeatReservationRepository seatReservationRepository;
    @Autowired
    private DataSource dataSource;

    @Override
    public byte[] generateInvoice(Long seatId, Long userId, Long scheduleId)
            throws JRException, SQLException
    {
        Map<String, Object> params = new HashMap<>();
        SeatReservation seatReservation = seatReservationRepository.getReferenceById(
            new SeatReservationId(userId, seatId, scheduleId)
        );
        Schedule schedule = scheduleRepository.getReferenceById(seatReservation.getSeatReservationId().getScheduleId());

        params.put("scheduleId", seatReservation.getSeatReservationId().getScheduleId());
        params.put("seatId", seatReservation.getSeatReservationId().getSeatId());
        params.put("filmId", schedule.getFilm().getId());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        JasperReport report = JasperCompileManager.compileReport(
                "src\\main\\resources\\invoice2.jrxml"
        );
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource.getConnection());

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        return stream.toByteArray();
    }
}
