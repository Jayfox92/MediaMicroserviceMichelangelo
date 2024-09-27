package com.michelangelo.mediamicroservice.services;

import com.michelangelo.mediamicroservice.entities.Genre;
import com.michelangelo.mediamicroservice.repositories.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class GenreServiceTest {

    @InjectMocks
    private GenreService genreService;

    @Mock
    private GenreRepository genreRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        // Arrange: Skapa testdata
        List<Genre> genres = new ArrayList<>();
        Genre genre1 = new Genre();
        genre1.setName("Rock");
        genres.add(genre1);

        Genre genre2 = new Genre();
        genre2.setName("Pop");
        genres.add(genre2);

        // Mocka repository-anropet
        when(genreRepository.findAll()).thenReturn(genres);

        // Act: Hämta alla genrer via service
        List<Genre> result = genreService.getAll();

        // Assert: Kontrollera att resultatet är korrekt
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyInAnyOrder(genre1, genre2);
    }
}
