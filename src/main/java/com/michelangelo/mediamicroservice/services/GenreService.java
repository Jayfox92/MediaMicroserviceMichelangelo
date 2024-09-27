package com.michelangelo.mediamicroservice.services;

import com.michelangelo.mediamicroservice.entities.Genre;
import com.michelangelo.mediamicroservice.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService implements GenreServiceInterface{
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }
}
