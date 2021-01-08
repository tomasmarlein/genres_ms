package com.example.genres_ms.controller;

import com.example.genres_ms.model.Movie_Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieGenreController {

    @Autowired
    private com.example.genres_ms.repository.MoviesGenresRepository MoviesGenresRepository;

    @GetMapping("/movies/genre/{genre_uuid}")
    public List<Movie_Genre> getMoviesByGenre(@PathVariable String genre_uuid){
        return MoviesGenresRepository.getMoviesByGenre(genre_uuid);
    }
}
