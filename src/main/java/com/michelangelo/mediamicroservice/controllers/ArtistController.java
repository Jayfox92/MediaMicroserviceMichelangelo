package com.michelangelo.mediamicroservice.controllers;

import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    // Endpoint: Hämta alla album för en artist med id och skicka tillbaka en lista med album objekt
/*
    @GetMapping("/getAllAlbumsWithArtist/{id}"){
        public ResponseEntity<List<Album>> getAllAlbums(@PathVariable ("id") int id){

        }
    }
*/

    @GetMapping("/getmediabyartist/{id}")
    public ResponseEntity<List<Media>> getMediaByArtist(@PathVariable("id") long id){
        return ResponseEntity.ok(artistService.getMediaByArtist(id));
    }


}
