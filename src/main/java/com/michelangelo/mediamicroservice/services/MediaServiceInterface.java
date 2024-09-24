package com.michelangelo.mediamicroservice.services;

import com.michelangelo.mediamicroservice.entities.Media;

import java.util.List;

public interface MediaServiceInterface {
    Media getMedia(Long mediaId,Long userId);
    Media getMediaById(Long mediaId);
    List<Media> getAllMedia();

    // Hämta media baserat på genreId
    List<Media> findMediaByGenreId(Long genreId);

    List<Media> getAllMedia();

}
