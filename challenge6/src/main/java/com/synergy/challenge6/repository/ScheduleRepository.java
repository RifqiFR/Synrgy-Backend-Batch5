package com.synergy.challenge6.repository;

import com.synergy.challenge6.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository  extends JpaRepository<Schedule, Long> {
    List<Schedule> findByFilmId(long filmId);
}
