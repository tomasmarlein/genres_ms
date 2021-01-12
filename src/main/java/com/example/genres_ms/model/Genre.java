package com.example.genres_ms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    @Column(unique=true)
    private String uuid;
    private String name;

    public Genre(){}
    public Genre(UUID uuid, String name){
        this.uuid = uuid.toString();
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getUuid() {
        return uuid;
    }
    public String getName() {
        return name;
    }

}
