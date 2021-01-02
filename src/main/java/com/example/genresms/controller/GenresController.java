package com.example.genresms.controller;

import com.example.genresms.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenresController {

    @Autowired
    private com.example.genresms.repository.GenresRepository GenresRepository;
    
    @GetMapping("/genres/all")
    public List<Genre> getAllGenres(){
        return GenresRepository.findAll();
    }

    @GetMapping("/genres/{uuid}")
    public List<Genre> getGenresByUuid(@PathVariable String uuid){
        return GenresRepository.findGenresByUuid(uuid);
    }

}
