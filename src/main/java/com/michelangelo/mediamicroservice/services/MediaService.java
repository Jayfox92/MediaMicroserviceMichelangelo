package com.michelangelo.mediamicroservice.services;

import com.michelangelo.mediamicroservice.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaService implements MediaServiceInterface{
    @Autowired
    MediaRepository mediaRepository;
}
