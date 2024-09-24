package com.michelangelo.mediamicroservice.controllers;

import com.michelangelo.mediamicroservice.entities.Genre;
import com.michelangelo.mediamicroservice.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("media/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping("/getall")
    public ResponseEntity<List<Genre>> getAllGenres(){
        return ResponseEntity.ok(genreService.getAll());
    }

}
