package com.michelangelo.mediamicroservice.controllers;

import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaRepository mediaRepository;


    /**
     * Endpoint to retrieve a Media object by its ID.
     * The data is fetched directly from the database via JPA.
     * In the future, you might extend this to fetch additional data from other microservices.
     */
    @GetMapping("/{id}")
    public Media getMedia(@PathVariable Long id) {
        return mediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media not found"));
    }
    // Additional methods can be added here to handle communication with other microservices
}
