package com.michelangelo.mediamicroservice.services;

import com.michelangelo.mediamicroservice.entities.Genre;

import java.util.List;

public interface GenreServiceInterface {
    List<Genre> getAll();
}
