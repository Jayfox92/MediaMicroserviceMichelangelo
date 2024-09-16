package com.michelangelo.mediamicroservice.controllers;

import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/media/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;


    /**
     * Endpoint to retrieve a Media object by its ID.
     * The data is fetched directly from the database via JPA.
     * In the future, you might extend this to fetch additional data from other microservices.
     */

    @GetMapping("/{mediaId}/{userId}")
    public ResponseEntity<Media> getMediaByMediaIdAndUserId(@PathVariable Long mediaId,@PathVariable Long userId) {
        return ResponseEntity.ok(mediaService.getMedia(mediaId,userId));
    }

    @GetMapping("/{mediaId}")
    public ResponseEntity<Media>  getMediaByMediaId(@PathVariable Long mediaId) {
        return  ResponseEntity.ok(mediaService.getMediaById(mediaId));
    }

}


