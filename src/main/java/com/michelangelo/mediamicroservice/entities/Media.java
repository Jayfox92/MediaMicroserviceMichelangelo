package com.michelangelo.mediamicroservice.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40, nullable = false)
    private String title;
    private LocalDate releaseDate;
    @ManyToMany
    @JoinTable(
            name = "media_genre",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @JsonIgnoreProperties(value = "listOfMedia")
    private List<Genre> genres;

    @ManyToOne
    @JoinColumn(name = "type_of_media_id")
    private TypeOfMedia typeOfMedia;

    @ManyToOne
    @JoinColumn(name = "number_on_album_id")
    @JsonIgnoreProperties(value = "album")
    private NumberOnAlbum numberOnAlbum;

    @ManyToMany
    @JoinTable(
            name = "media_artist",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    @JsonProperty("artists")
    @JsonIgnoreProperties({"createdMedia", "albums"})
    private List<Artist> artists;

    @ManyToOne
    @JoinColumn(name = "album_id")
    @JsonProperty("album")
    private Album album;

    @Column(nullable = false, length = 100)
    @JsonProperty("url")
    private String url;


    public Media() {
    }

    public Media(Long id, String title) {
        this.id = id;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }


    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
