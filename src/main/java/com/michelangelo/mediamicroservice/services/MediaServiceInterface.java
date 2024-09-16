package com.michelangelo.mediamicroservice.services;

import com.michelangelo.mediamicroservice.entities.Media;

public interface MediaServiceInterface {
    Media getMedia(Long mediaId,Long userId);
    Media getMediaById(Long mediaId);

}
