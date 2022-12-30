package com.synergy.challenge6.model;

import com.synergy.challenge6.model.id.SeatReservationId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "seat_reservations")
@NoArgsConstructor
public class SeatReservation {
    @EmbeddedId
    private SeatReservationId seatReservationId;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "schedule_id")
//    private Schedule schedule;
//
//    @ManyToOne
//    @JoinColumn(name = "seat_id")
//    private Seat seat;
}
