package com.michelangelo.mediamicroservice.services;

import com.michelangelo.mediamicroservice.entities.Album;
import com.michelangelo.mediamicroservice.entities.Media;

import java.util.List;

public interface ArtistServiceInterface {
    List<Album> getAllAlbums(long id);
    List<Media> getMediaByArtist(long id);
}
