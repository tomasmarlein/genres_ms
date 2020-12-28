package fact.it.genresms.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Genre")
public class Genre {
    @Id
    private Integer id;
    private Integer uuid;
    private Integer movieId;
    private String name;

    public Genre() {
    }

    public Genre(Integer id, Integer uuid, Integer movieId, String name) {
        this.id = id;
        this.uuid = uuid;
        this.movieId = movieId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
