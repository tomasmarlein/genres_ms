package fact.it.genresms.controller;

import fact.it.genresms.model.Genres;
import fact.it.genresms.repository.GenresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class GenresController {

    @Autowired
    private GenresRepository GenresRepository;


    @GetMapping("/genres/all")
    public List<Genres> getAllGenres(){
        return GenresRepository.findAll();
    }

    @GetMapping("/genres/{uuid}")
    public List<Genres> getGenresByUuid(@PathVariable String uuid){
        return GenresRepository.findGenresByUuid(uuid);
    }

}
