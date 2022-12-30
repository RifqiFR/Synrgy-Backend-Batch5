package com.synergy.challenge6.repository;

import com.synergy.challenge6.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query(nativeQuery = true, value = "SELECT films.* FROM films "
        + "WHERE sedang_tayang")
    List<Film> findAiringFilms();
}
