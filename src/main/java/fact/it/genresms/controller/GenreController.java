package fact.it.genresms.controller;

import fact.it.genresms.model.Genre;
import fact.it.genresms.repository.GenresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class GenreController {

    @Autowired
    private GenresRepository GenresRepository;


    @GetMapping("/genres/all")
    public List<Genre> getAllGenres(){
        return GenresRepository.findAll();
    }

    @GetMapping("/genres/{uuid}")
    public List<Genre> getGenreByUuid(@PathVariable String uuid){
        return GenresRepository.findGenresByUuid(uuid);
    }

}
