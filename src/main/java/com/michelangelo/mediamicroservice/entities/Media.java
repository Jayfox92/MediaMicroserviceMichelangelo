package com.michelangelo.mediamicroservice.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate releaseDate;
    @ManyToMany(targetEntity = Genre.class)
    private List<Genre> genres;
    @ManyToOne(targetEntity = TypeOfMedia.class)
    private TypeOfMedia typeOfMedia;
    @OneToOne(targetEntity = NumberOnAlbum.class)
    private NumberOnAlbum numberOnAlbum;

    public Media() {
    }

    public NumberOnAlbum getNumberOnAlbum() {
        return numberOnAlbum;
    }

    public void setNumberOnAlbum(NumberOnAlbum numberOnAlbum) {
        this.numberOnAlbum = numberOnAlbum;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public TypeOfMedia getTypeOfMedia() {
        return typeOfMedia;
    }

    public void setTypeOfMedia(TypeOfMedia typeOfMedia) {
        this.typeOfMedia = typeOfMedia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
