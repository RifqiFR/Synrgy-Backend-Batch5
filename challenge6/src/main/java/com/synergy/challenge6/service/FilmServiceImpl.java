package com.synergy.challenge6.service;

import com.synergy.challenge6.model.Film;
import com.synergy.challenge6.repository.FilmRepository;
import com.synergy.challenge6.repository.ScheduleRepository;
import com.synergy.challenge6.view.APIResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Log4j2
@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Map createFilm(Film film) {
        try{
            return APIResponse.success(
                filmRepository.save(film),
                201
            );
        }catch(Exception e) {
            return APIResponse.error("Film gagal dibuat", 400);
        }
    }

    @Override
    public Map updateNamaFilm(long id, String namaBaru) {
        try{
            Film film = filmRepository.getReferenceById(id);
            film.setName(namaBaru);

            return APIResponse.success(
                    filmRepository.save(film),
                    200
            );
        }catch(Exception e) {;
            return APIResponse.error("Nama film gagal diubah", 400);
        }
    }

    @Override
    public Map deleteFilm(long id) {
        try{
            filmRepository.deleteById(id);

            return APIResponse.success(
                    null,
                    204
            );
        }catch(Exception e) {
            return APIResponse.error("Film gagal dihapus", 400);
        }
    }

    @Override
    public Map getFilmYangSedangtayang() {
        try{
            return APIResponse.success(
                    filmRepository.findAiringFilms(),
                    200
            );
        }catch(Exception e) {
            log.debug(e.getMessage());
            return APIResponse.error("Error", 400);
        }
    }

    @Override
    public Map getFilmSchedule(long filmId) {
        try{
            return APIResponse.success(
                    scheduleRepository.findByFilmId(filmId),
                    200
            );
        }catch(Exception e) {
            return APIResponse.error("Error", 400);
        }
    }

    @Override
    public Map getAll() {
        try{
            return APIResponse.success(
                    filmRepository.findAll(),
                    200
            );
        }catch(Exception e) {
            return APIResponse.error("Error", 400);
        }
    }
}
