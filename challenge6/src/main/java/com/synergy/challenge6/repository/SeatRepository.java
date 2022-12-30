package com.synergy.challenge6.repository;

import com.synergy.challenge6.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
