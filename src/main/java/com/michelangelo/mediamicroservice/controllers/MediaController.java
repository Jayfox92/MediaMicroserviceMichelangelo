package com.michelangelo.mediamicroservice.controllers;

import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.repositories.MediaRepository;
import com.michelangelo.mediamicroservice.services.MediaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/v1/media")
public class MediaController {

    @Autowired
    private MediaServiceInterface mediaService;


    /**
     * Endpoint to retrieve a Media object by its ID.
     * The data is fetched directly from the database via JPA.
     * In the future, you might extend this to fetch additional data from other microservices.
     */

    @GetMapping("/{mediaId}/{userId}")
    public ResponseEntity<Media> getMediaById(@PathVariable Long mediaId,@PathVariable Long userId) {
        return ResponseEntity.ok(mediaService.getMediaById(mediaId,userId));
    }

    /*Pre-security config/logging of media for user
    @GetMapping("/{id}")
    public Media getMedia(@PathVariable Long id) {
        return mediaService.getMediaById(id);
    }*/


    // Additional methods can be added here to handle communication with other microservices
}


