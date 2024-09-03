package com.michelangelo.mediamicroservice.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany(targetEntity = NumberOnAlbum.class)
    List<NumberOnAlbum> songs;

    public Album() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<NumberOnAlbum> getSongs() {
        return songs;
    }

    public void setSongs(List<NumberOnAlbum> songs) {
        this.songs = songs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
