package com.michelangelo.mediamicroservice.entities;

import jakarta.persistence.*;

@Entity
public class NumberOnAlbum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int numberOnAlbum;
    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    public NumberOnAlbum() {
    }

    public int getNumberOnAlbum() {
        return numberOnAlbum;
    }

    public void setNumberOnAlbum(int numberOnAlbum) {
        this.numberOnAlbum = numberOnAlbum;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
