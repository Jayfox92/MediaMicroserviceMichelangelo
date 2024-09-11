package com.michelangelo.mediamicroservice.services;

import com.michelangelo.mediamicroservice.entities.Media;

public interface MediaServiceInterface {
    Media getMediaById(Long id,String username);
}
