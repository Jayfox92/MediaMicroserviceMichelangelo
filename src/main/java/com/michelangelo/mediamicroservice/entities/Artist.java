package com.michelangelo.mediamicroservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column (length = 40, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "artists")

    @JsonIgnoreProperties(value = "artists")
    private List<Media> createdMedia;

    @ManyToMany
    @JoinTable(
            name = "artist_albums",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id")
    )
    @JsonIgnoreProperties(value = "artist")
    private List<Album> albums = new ArrayList<>();


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
