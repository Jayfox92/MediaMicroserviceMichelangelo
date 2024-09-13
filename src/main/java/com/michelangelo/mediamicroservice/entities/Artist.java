package com.michelangelo.mediamicroservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
//@JsonIgnoreProperties("createdMedia")
public class Artist {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column (length = 40, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "artists")
    /*@JoinTable(
            name = "media_artist", // Join-table name
            joinColumns = @JoinColumn(name = "media_id"), // Column in media_artist table that references Media
            inverseJoinColumns = @JoinColumn(name = "artist_id") // Column in media_artist table that references Artist
    )*/
    @JsonIgnoreProperties(value = "artists")
    private List<Media> createdMedia;

    @OneToMany(mappedBy = "artist")
    @JsonIgnoreProperties(value = "artist")
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
