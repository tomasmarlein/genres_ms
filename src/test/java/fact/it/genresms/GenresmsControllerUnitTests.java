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
        Genre genre1 = new Genre("964df97f-2cd4-4e1a-acf9-c21b2ad1e947",  "genre 1");
        Genre genre2 = new Genre("964df97f-2cd4-4e1a-acf9-c21b2ad1e948",  "genre 2");

        List<Genre> genreList = new ArrayList<>();
        genreList.add(genre1);
        genreList.add(genre2);

        given(genreRepository.findGenresByUuid("964df97f-2cd4-4e1a-acf9-c21b2ad1e947")).willReturn(genreList);

        mockMvc.perform(get("/genres/{uuid}", "964df97f-2cd4-4e1a-acf9-c21b2ad1e947"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].uuid", is(1)))
                .andExpect(jsonPath("$[0].movieId", is(1)))
                .andExpect(jsonPath("$[0].name", is("genre 1")))
                .andExpect(jsonPath("$[1].uuid", is(2)))
                .andExpect(jsonPath("$[1].movieId", is(1)))
                .andExpect(jsonPath("$[1].name", is("genre 2")));
    }

    @Test
    public void givenGenre_whenGetAllGenres_thenReturnJsonGenres() throws Exception {
        Genre genre1 = new Genre("964df97f-2cd4-4e1a-acf9-c21b2ad1e947", "genre 1");
        Genre genre2 = new Genre("964df97f-2cd4-4e1a-acf9-c21b2ad1e948", "genre 3");

        List<Genre> genreList = new ArrayList<>();
        genreList.add(genre1);
        genreList.add(genre2);

        given(genreRepository.findAll()).willReturn(genreList);

        mockMvc.perform(get("/genres/all"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].uuid", is("964df97f-2cd4-4e1a-acf9-c21b2ad1e947")))
                .andExpect(jsonPath("$[0].name", is("genre 1")))
                .andExpect(jsonPath("$[1].uuid", is("964df97f-2cd4-4e1a-acf9-c21b2ad1e948")))
                .andExpect(jsonPath("$[1].name", is("genre 2")));
    }



}
