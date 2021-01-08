package com.example.genres_ms.repository;

import com.example.genres_ms.model.Movie_Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesGenresRepository extends JpaRepository<Movie_Genres, Integer> {
    List<Movie_Genres> getMoviesByGenre_uuid(String genre_uuid);
}
