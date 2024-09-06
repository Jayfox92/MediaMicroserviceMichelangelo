package com.michelangelo.mediamicroservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "numberOnAlbum"})
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40)
    private String title;
    private LocalDate releaseDate;
    @ManyToMany
    @JoinTable(
            name = "media_genre", // Namnet på join-tabellen
            joinColumns = @JoinColumn(name = "media_id"), // Kolumn som refererar till Media
            inverseJoinColumns = @JoinColumn(name = "genre_id") // Kolumn som refererar till Genre
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
    @JsonIgnoreProperties(value = "createdMedia")
    private List<Artist> artists;

    @ManyToOne
    @JoinColumn(name = "album_id")
    @JsonProperty("album")
    private Album album;

    @Column(nullable = false, length = 100)
    @JsonProperty("url") // This will ensure 'url' is included in JSON output
    private String url;


    // This list will store the IDs of the artists related to this media
    // Later, these IDs can be used to fetch full artist details from the Artist microservice
    /*@ElementCollection
    @CollectionTable(name = "media_artist_ids", joinColumns = @JoinColumn(name = "media_id"))
    @Column(name = "artist_id")
    private List<Long> artistIds;*/
    /*public List<Long> getArtistIds() { return artistIds; }

    public void setArtistIds(List<Long> artistIds) { this.artistIds = artistIds; }*/


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
