package com.synergy.challenge6.service;

import com.synergy.challenge6.model.Film;

import java.util.Map;

public interface FilmService {
    Map createFilm(Film film);
    Map updateNamaFilm(long id, String namaBaru);
    Map deleteFilm(long id);
    Map getFilmYangSedangtayang();
    Map getFilmSchedule(long filmId);
    Map getAll();
}
