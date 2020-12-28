package fact.it.genresms;

import fact.it.genresms.model.Genre;
import fact.it.genresms.repository.GenresRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GenresmsControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GenresRepository genreRepository;

    private Genre genre1Movie1 = new Genre(1, 1, 1,"genre 1 movie 1");
    private Genre genre2Movie2 = new Genre(2, 2, 2,"genre 2 movie 2");
    private Genre genre3Movie2 = new Genre(3, 2, 3,"genre 3 movie 2");
    private Genre genreToBeDeleted = new Genre(4, 3, 4,"genre 4 movie 3 to be deleted");

    @BeforeEach
    public void beforeAllTests() {
        genreRepository.deleteAll();
        genreRepository.save(genre1Movie1);
        genreRepository.save(genre2Movie2);
        genreRepository.save(genre3Movie2);
        genreRepository.save(genreToBeDeleted);
    }

    @AfterEach
    public void afterAllTests() {
        genreRepository.deleteAll();
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenGenre_whenGetGenresByMovieUuid_thenReturnJsonGenres() throws Exception {
        mockMvc.perform(get("/genres/movie/{movieUuid}", 2))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].uuid", is(2)))
                .andExpect(jsonPath("$[0].movieId", is(2)))
                .andExpect(jsonPath("$[0].name", is("genre 2 movie 2")))
                .andExpect(jsonPath("$[1].uuid", is(3)))
                .andExpect(jsonPath("$[1].movieId", is(2)))
                .andExpect(jsonPath("$[1].name", is("genre 3 movie 2")));
    }

    @Test
    public void givenGenre_whenGetAllGenres_thenReturnJsonGenres() throws Exception {
        mockMvc.perform(get("/genres/all"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].uuid", is(1)))
                .andExpect(jsonPath("$[0].movieId", is(1)))
                .andExpect(jsonPath("$[0].name", is("genre 1 movie 1")))
                .andExpect(jsonPath("$[1].uuid", is(2)))
                .andExpect(jsonPath("$[1].movieId", is(2)))
                .andExpect(jsonPath("$[1].name", is("genre 2 movie 2")))
                .andExpect(jsonPath("$[2].uuid", is(3)))
                .andExpect(jsonPath("$[2].movieId", is(2)))
                .andExpect(jsonPath("$[2].name", is("genre 3 movie 2")))
                .andExpect(jsonPath("$[3].uuid", is(4)))
                .andExpect(jsonPath("$[3].movieId", is(3)))
                .andExpect(jsonPath("$[3].name", is("genre 4 movie 3 to be deleted")));
    }

    @Test
    public void whenPostGenre_thenReturnJsonGenre() throws Exception {
        Genre genre5Movie4 = new Genre(5, 4, 2,"genre 5 movie 4");

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
        Genre updatedGenre = new Genre(2, 2, 1, "James Bond");

        mockMvc.perform(put("/genres")
                .content(mapper.writeValueAsString(updatedGenre))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(2)))
                .andExpect(jsonPath("$.movieId", is(2)))
                .andExpect(jsonPath("$.name", is("updated")));
    }

    @Test
    public void givenGenre_whenDeleteGenre_thenStatusOk() throws Exception {
        mockMvc.perform(delete("/genres/{uuid}", 4)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoGenre_whenDeleteGenre_thenStatusNotFound() throws Exception {
        mockMvc.perform(delete("/genres/{uuid}", 100)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
