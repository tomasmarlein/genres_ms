package com.example.genres_ms;

import com.example.genres_ms.repository.GenresRepository;
import com.example.genres_ms.model.Genre;
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

        given(genreRepository.findFirstByUuid(genre.getUuid())).willReturn(genre);

        mockMvc.perform(get("/genres/{uuid}", genre.getUuid()))
                .andExpect(jsonPath("$.uuid", is(genre.getUuid())))
                .andExpect(jsonPath("$.name", is(genre.getName())))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
