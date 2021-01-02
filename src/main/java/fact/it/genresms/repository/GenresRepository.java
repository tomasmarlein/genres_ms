package fact.it.genresms.repository;

import fact.it.genresms.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenresRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findGenresByUuid(String uuid);
}
