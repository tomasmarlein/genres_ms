package com.example.genres_ms.controller;

import com.example.genres_ms.model.Movie_Genres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieGenreController {

    @Autowired
    private com.example.genres_ms.repository.MoviesGenresRepository moviesGenresRepository;

    @GetMapping("/genre/movies/{genre_uuid}")
    public List<Movie_Genres> getMoviesByGenre(@PathVariable String genre_uuid){
        return moviesGenresRepository.getMoviesByGenre_uuid(genre_uuid);
    }
}
