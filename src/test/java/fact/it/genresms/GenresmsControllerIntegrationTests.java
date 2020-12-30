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
    private int initialNumberGenre = 0;

    @Autowired
    private GenresRepository genreRepository;

    private Genre genre1 = new Genre("964df97f-2cd4-4e1a-acf9-c21b2ad1e947", "genre 1");
    private Genre genre2 = new Genre("964df97f-2cd4-4e1a-acf9-c21b2ad1e948", "genre 2");
    private Genre genre3 = new Genre("964df97f-2cd4-4e1a-acf9-c21b2ad1e949", "genre 3");
    private Genre genreToBeDeleted = new Genre("964df97f-2cd4-4e1a-acf9-c21b2ad1e950", "genre 4 to be deleted");

    @BeforeEach
    public void beforeAllTests() {
        initialNumberGenre = genreRepository.findAll().size();
        genreRepository.save(genre1);
        genreRepository.save(genre2);
        genreRepository.save(genre3);
        genreRepository.save(genreToBeDeleted);
    }

    @AfterEach
    public void afterAllTests() {
        genreRepository.deleteById(genre1.getId());
        genreRepository.deleteById(genre2.getId());
        genreRepository.deleteById(genre3.getId());
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void requestAllItemsOnGenre_thenReturnJsonGenreList() throws Exception {
        mockMvc.perform(get("/genres/all"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(initialNumberGenre + 3)));
    }

    @Test
    public void requestOneGenreItem_thenReturnJsonGenre() throws Exception {
        mockMvc.perform(get("/genres/{uuid}", "964df97f-2cd4-4e1a-acf9-c21b2ad1e947"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is("964df97f-2cd4-4e1a-acf9-c21b2ad1e947")))
        ;
    }

}
