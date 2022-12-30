package com.synergy.challenge6.model.id;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Embeddable
@NoArgsConstructor
public class SeatReservationId implements Serializable {
    private long userId;
    private long scheduleId;
    private long seatId;
}
