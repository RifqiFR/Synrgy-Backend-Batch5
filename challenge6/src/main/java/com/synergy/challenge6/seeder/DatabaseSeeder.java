package com.synergy.challenge6.seeder;

import com.synergy.challenge6.model.*;
import com.synergy.challenge6.model.id.SeatReservationId;
import com.synergy.challenge6.repository.*;
import com.synergy.challenge6.staticvalue.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private SeatReservationRepository seatReservationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        //if already seeded, dont seed
        if(seatReservationRepository.count() != 0)
            return;
        String defaultPassword = passwordEncoder.encode("password");
        User user = new User(0, "username", "email", defaultPassword , Role.ADMIN);
        userRepository.save(user);
        user = new User(0, "username", "email2", defaultPassword , Role.CUSTOMER);
        user = userRepository.save(user);

        Film film = new Film(0, "Code", "Judul Film tidak tayang", false);
        film = filmRepository.save(film);

        film = new Film(0, "Code", "Judul Film", true);
        film = filmRepository.save(film);

        Schedule schedule = new Schedule(
                0, film, LocalDateTime.now(), LocalDateTime.now().plusHours(2)
        );
        scheduleRepository.save(schedule);
        Schedule schedule2 = new Schedule(
                0, film, LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(4)
        );
        schedule2 = scheduleRepository.save(schedule2);

        Seat seat = new Seat(0, "nama studio", "1");
        seat = seatRepository.save(seat);

        SeatReservationId seatReservationId = new SeatReservationId(
                user.getId(), schedule2.getId(), seat.getId()
        );
        SeatReservation seatReservation = new SeatReservation(seatReservationId);
        seatReservationRepository.save(seatReservation);
    }
}
