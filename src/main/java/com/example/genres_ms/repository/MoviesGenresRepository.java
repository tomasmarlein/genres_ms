package com.example.genres_ms.repository;

import com.example.genres_ms.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesGenresRepository extends JpaRepository<Genre, Integer> {
    List<Genre> getMoviesByGenre(String genre_uuid);
}
