package com.synergy.challenge6.repository;

import com.synergy.challenge6.model.SeatReservation;
import com.synergy.challenge6.model.id.SeatReservationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatReservationRepository extends JpaRepository<SeatReservation, SeatReservationId> {
}
