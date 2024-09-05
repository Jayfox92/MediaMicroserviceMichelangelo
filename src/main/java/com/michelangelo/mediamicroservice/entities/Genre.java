package com.michelangelo.mediamicroservice.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany//(mappedBy = "genres")
    private List<Media> listOfMedia;

    public Genre() {
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
