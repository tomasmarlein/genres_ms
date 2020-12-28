package fact.it.genresms;

import fact.it.genresms.model.Genre;
import fact.it.genresms.repository.GenresRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GenresmsControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenresRepository genreRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenGenre_whenGetGenresByMovieUuid_thenReturnJsonGenres() throws Exception {
        Genre genre1Movie1 = new Genre(1, 1, 1, "genre 1 movie 1");
        Genre genre2Movie1 = new Genre(2, 1, 2, "genre 2 movie 1");

        List<Genre> genreList = new ArrayList<>();
        genreList.add(genre1Movie1);
        genreList.add(genre2Movie1);

        given(genreRepository.findGenresWithMovieId(1)).willReturn(genreList);

        mockMvc.perform(get("/genres/movie/{movieUuid}", 1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].uuid", is(1)))
                .andExpect(jsonPath("$[0].movieId", is(1)))
                .andExpect(jsonPath("$[0].name", is("genre 1 movie 1")))
                .andExpect(jsonPath("$[1].uuid", is(2)))
                .andExpect(jsonPath("$[1].movieId", is(1)))
                .andExpect(jsonPath("$[1].name", is("genre 2 movie 1")));
    }

    @Test
    public void givenGenre_whenGetAllGenres_thenReturnJsonGenres() throws Exception {
        Genre genre1Movie1 = new Genre(1, 1, 1, "genre 1 movie 1");
        Genre genre2Movie1 = new Genre(2, 1, 1, "genre 2 movie 1"));

        List<Genre> genreList = new ArrayList<>();
        genreList.add(genre1Movie1);
        genreList.add(genre2Movie1);

        given(genreRepository.findAll()).willReturn(genreList);

        mockMvc.perform(get("/genres/all"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].uuid", is(1)))
                .andExpect(jsonPath("$[0].movieId", is(1)))
                .andExpect(jsonPath("$[0].name", is("genre 1 movie 1")))
                .andExpect(jsonPath("$[1].uuid", is(2)))
                .andExpect(jsonPath("$[1].movieId", is(1)))
                .andExpect(jsonPath("$[1].name", is("genre 2 movie 1")));
    }

    @Test
    public void whenPostGenre_thenReturnJsonGenre() throws Exception {
        Genre genre1Movie1 = new Genre(1, 1, 1, "genre 1 movie 1");

        mockMvc.perform(post("/genres")
                .content(mapper.writeValueAsString(genre1Movie1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(1)))
                .andExpect(jsonPath("$.movieId", is(1)))
                .andExpect(jsonPath("$.name", is("genre 1 movie 1")));
    }

    @Test
    public void givenGenre_whenPutGenre_thenReturnJsonGenre() throws Exception {
        Genre genre1Movie1 = new Genre(1, 1, 1, "genre 1 movie 1");

        given(genreRepository.findGenreByUuid(1)).willReturn(genre1Movie1);

        Genre updatedGenre = new Genre(1, 1, 1, "genre 1 movie 1 updated");

        mockMvc.perform(put("/genres")
                .content(mapper.writeValueAsString(updatedGenre))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(1)))
                .andExpect(jsonPath("$.movieId", is(1)))
                .andExpect(jsonPath("$.name", is("genre 1 movie 1 updated")));
    }

    @Test
    public void givenGenre_whenDeleteGenre_thenStatusOk() throws Exception {
        Genre genreToBeDeleted = new Genre(1, 1, 1,"genre to delete");

        given(genreRepository.findGenreByUuid(1)).willReturn(genreToBeDeleted);

        mockMvc.perform(delete("/genres/{uuid}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoGenre_whenDeleteGenre_thenStatusNotFound() throws Exception {
        given(genreRepository.findGenreByUuid(1000)).willReturn(null);

        mockMvc.perform(delete("/genres/{uuid}", 1000)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
