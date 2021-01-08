package com.example.genres_ms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
public class Movie_Genre {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    private String movie_uuid;
    private String genre_uuid;

    public Movie_Genre() {}
    public Movie_Genre(String movie_uuid, String genre_uuid) {
        this.movie_uuid = movie_uuid;
        this.genre_uuid = genre_uuid;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovie_uuid() {
        return movie_uuid;
    }

    public void setMovie_uuid(String movie_uuid) {
        this.movie_uuid = movie_uuid;
    }

    public String getGenre_uuid() {
        return genre_uuid;
    }

    public void setGenre_uuid(String genre_uuid) {
        this.genre_uuid = genre_uuid;
    }
}
