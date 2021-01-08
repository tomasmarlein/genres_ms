package com.example.genres_ms;

import com.example.genres_ms.model.Movie_Genre;
import com.example.genres_ms.repository.GenresRepository;
import com.example.genres_ms.model.Genre;
import com.example.genres_ms.repository.MoviesGenresRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GenresMSControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenresRepository genreRepository;
    private MoviesGenresRepository moviesgenresRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void requestAllItemsOnGenre_thenReturnJsonGenreList() throws Exception {
        Genre genre = new Genre(UUID.fromString("964df97f-2cd4-4e1a-acf9-c21b2ad1e947"), "genre 1");

        List<Genre> genreList = Collections.singletonList(genre);


        given(genreRepository.findAll()).willReturn(genreList);

        mockMvc.perform(get("/genres/all"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }


    @Test
    public void requestOneGenreItem_thenReturnJsonGenre() throws Exception {
        Genre genre = new Genre(UUID.fromString("964df97f-2cd4-4e1a-acf9-c21b2ad1e947"),  "genre 1");

        List<Genre> genreList = Collections.singletonList(genre);

        given(genreRepository.findGenresByUuid("964df97f-2cd4-4e1a-acf9-c21b2ad1e947")).willReturn(genreList);

        mockMvc.perform(get("/genres/{uuid}", genre.getUuid()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void requestAllItemsOnMoviesGenre_thenReturnJsonMoviesGenreList() throws Exception {
        Movie_Genre movie_genre = new Movie_Genre("2f48b395-b7ea-47cd-a503-e8f1cb630958", "964df97f-2cd4-4e1a-acf9-c21b2ad1e947");

        List<Movie_Genre> movie_genreList = Collections.singletonList(movie_genre);


        given(moviesgenresRepository.findAll()).willReturn(movie_genreList);

        mockMvc.perform(get("/movies/genre/{genre_uuid}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

}
