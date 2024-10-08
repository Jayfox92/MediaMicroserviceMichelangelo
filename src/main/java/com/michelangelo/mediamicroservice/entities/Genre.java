package com.michelangelo.mediamicroservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    @ManyToMany(mappedBy = "genres")
    @JsonIgnoreProperties(value = "genres")
    private List<Media> listOfMedia;


    public Genre() {
    }

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Media> getListOfMedia() {
        return listOfMedia;
    }

    public void setListOfMedia(List<Media> listOfMedia) {
        this.listOfMedia = listOfMedia;
    }
}
