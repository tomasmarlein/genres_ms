package fact.it.genresms.repository;

import fact.it.genresms.model.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface GenresRepository extends JpaRepository<Genres, Integer> {
    List<Genres> findGenresByUuid(String uuid);
}
