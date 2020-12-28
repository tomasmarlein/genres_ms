package fact.it.genresms.repository;

import fact.it.genresms.model.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenresRepository extends MongoRepository<Genre, String> {
    List<Genre> getAllWithMovieId(Integer movieId);
    List<Genre> findGenresWithMovieId(Integer movieId);
}
