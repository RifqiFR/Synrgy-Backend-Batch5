package com.synergy.challenge6.controller;

import com.synergy.challenge6.model.Film;
import com.synergy.challenge6.service.FilmService;
import com.synergy.challenge6.view.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("film")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @PostMapping
    public ResponseEntity<Map> store(Film film) {
        Map response = filmService.createFilm(film);

        return new ResponseEntity<>(
            response,
            HttpStatusCode.valueOf((Integer) response.get(APIResponse.STATUS))
        );
    }

    @GetMapping
    public ResponseEntity<Map> index(@RequestParam(required = false) String sedangTayang) {
        Map response;

        if(sedangTayang == null)
            response = filmService.getAll();
        else
            response = filmService.getFilmYangSedangtayang();

        return new ResponseEntity<>(
            response,
            HttpStatusCode.valueOf((Integer) response.get(APIResponse.STATUS))
        );
    }

    @GetMapping("/{filmId}/schedules")
    public ResponseEntity<Map> filmSchedule(@PathVariable long filmId) {
        Map response = filmService.getFilmSchedule(filmId);

        return new ResponseEntity<>(
                response,
                HttpStatusCode.valueOf((Integer) response.get(APIResponse.STATUS))
        );
    }

    @DeleteMapping("/{filmId}")
    public ResponseEntity<Object> delete(@PathVariable long filmId) {
        Map response = filmService.deleteFilm(filmId);

        return new ResponseEntity<>(
                response,
                HttpStatusCode.valueOf((Integer) response.get(APIResponse.STATUS))
        );
    }

    @PutMapping("/{filmId}")
    public ResponseEntity<Map> store(@PathVariable long filmId, String name) {
        Map response = filmService.updateNamaFilm(filmId, name);

        return new ResponseEntity<>(
                response,
                HttpStatusCode.valueOf((Integer) response.get(APIResponse.STATUS))
        );
    }
}
