package com.michelangelo.mediamicroservice.repositories;

import com.michelangelo.mediamicroservice.entities.Genre;
import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.entities.TypeOfMedia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = "/schema.sql")
class MediaRepositoryTest {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TypeOfMediaRepository typeOfMediaRepository;

    private Genre genre;
    private TypeOfMedia typeOfMedia;

    @BeforeEach
    void setUp() {

        genre = new Genre();
        genre.setName("Rock");
        genre = genreRepository.save(genre);


        typeOfMedia = new TypeOfMedia();
        typeOfMedia.setType("musik");
        typeOfMedia = typeOfMediaRepository.save(typeOfMedia);


        Media media = new Media();
        media.setTitle("Stairway to Heaven");
        media.setUrl("https://example.com/ledzeppelin/stairway_to_heaven");
        media.setGenres(List.of(genre));
        media.setTypeOfMedia(typeOfMedia);

        mediaRepository.save(media);
    }

    @Test
    void shouldFindMediaByGenresIdAndTypeOfMedia() {
        List<Media> result = mediaRepository.findByGenres_IdAndTypeOfMedia_Type(genre.getId(), typeOfMedia.getType());
        assertFalse(result.isEmpty());
        assertEquals("Stairway to Heaven", result.get(0).getTitle());
    }

    @Test
    void shouldReturnEmptyListWhenNoMediaFoundByGenreIdAndTypeOfMedia() {
        List<Media> result = mediaRepository.findByGenres_IdAndTypeOfMedia_Type(999L, "musik");
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindMediaByTypeOfMedia() {
        List<Media> result = mediaRepository.findByTypeOfMedia_Type("musik");
        assertFalse(result.isEmpty());
        assertEquals("Stairway to Heaven", result.get(0).getTitle());
    }

    @Test
    void shouldReturnEmptyListWhenNoMediaFoundByTypeOfMedia() {
        List<Media> result = mediaRepository.findByTypeOfMedia_Type("jazz");
        assertTrue(result.isEmpty());
    }
}
