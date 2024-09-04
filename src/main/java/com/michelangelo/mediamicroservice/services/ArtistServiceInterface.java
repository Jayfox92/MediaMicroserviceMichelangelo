package com.michelangelo.mediamicroservice.services;

import com.michelangelo.mediamicroservice.entities.Media;

import java.util.List;

public interface ArtistServiceInterface {
    List<Media> getMediaByArtist(long id);
}
