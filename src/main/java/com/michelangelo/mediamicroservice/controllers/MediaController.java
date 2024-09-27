package com.michelangelo.mediamicroservice.controllers;

import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.exceptions.CustomErrorResponse;
import com.michelangelo.mediamicroservice.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


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
    @GetMapping("/genre/{genreId}/{mediaType}")
    public ResponseEntity<List<Media>> getMediaByGenreId(@PathVariable Long genreId, @PathVariable String mediaType) {
        List<Media> mediaList = mediaService.findMediaByGenreId(genreId, mediaType);
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Media>> getAllMedia(){
        return ResponseEntity.ok(mediaService.getAllMedia());
    }


    // Hanterar ResponseStatusExceptions med en tydligare respons av CustomErrorResponse
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<CustomErrorResponse> handleExceptionResponse(ResponseStatusException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(new CustomErrorResponse(ex.getStatusCode(), ex.getReason(), ex.getMessage()));
    }


    @GetMapping("/getallbymediatype/{mediaType}")
    public ResponseEntity<List<Media>> getAllMediaByType(@PathVariable String mediaType) {
        return ResponseEntity.ok(mediaService.getAllMediaByType(mediaType));
    }
}