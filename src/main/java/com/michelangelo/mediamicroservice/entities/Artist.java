package com.michelangelo.mediamicroservice.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column (length = 40, nullable = false)
    private String name;

    @ManyToMany
    private List<Media> createdMedia;

    @OneToMany
    private List<Album> albums;

    public Artist(){}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Media> getCreatedMedia() {
        return createdMedia;
    }

    public void setCreatedMedia(List<Media> createdMedia) {
        this.createdMedia = createdMedia;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }


}
