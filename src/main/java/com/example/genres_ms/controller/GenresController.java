package com.example.genres_ms.controller;

import com.example.genres_ms.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenresController {

    @Autowired
    private com.example.genres_ms.repository.GenresRepository GenresRepository;

    @GetMapping("/genres/all")
    public List<Genre> getAllGenres(){
        return GenresRepository.findAll();
    }

    @GetMapping("/genres/{uuid}")
    public Genre getGenreByUUID(@PathVariable String uuid){
        return GenresRepository.findFirstByUuid(uuid);
    }

}
