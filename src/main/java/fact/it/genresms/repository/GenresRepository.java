package fact.it.genresms.repository;

import fact.it.genresms.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface GenresRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findAll();
    List<Genre> getAllByUuid(String uuid);
    List<Genre> findGenresByUuid(String uuid);
}
