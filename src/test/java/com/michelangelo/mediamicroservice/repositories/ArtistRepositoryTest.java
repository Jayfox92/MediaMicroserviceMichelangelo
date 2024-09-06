package com.michelangelo.mediamicroservice.repositories;

import com.michelangelo.mediamicroservice.entities.Artist;
import com.michelangelo.mediamicroservice.entities.Media;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.h2.console.enabled=true",
        "spring.h2.console.path=/h2-console",
        "spring.sql.init.mode=never",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class ArtistRepositoryTest {
    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    MediaRepository mediaRepository;

    @Test
    void findAllMediaByArtistId() {
        long searchId = 1;
        Artist artist1 = new Artist();
        artist1.setId(1L);
        artist1.setName("Kalle");
        List<Artist> artistList = new ArrayList<>();
        artistList.add(artist1);
        Media media1 = new Media();
        media1.setArtists(artistList);
        media1.setId(searchId);
        media1.setTitle("Title 1");
        List<Media> mediaList = new ArrayList<>();
        mediaList.add(media1);
        artist1.setCreatedMedia(mediaList);
        artistRepository.save(artist1);
        mediaRepository.save(media1);


        List<Media> result = artistRepository.findAllMediaByArtistId(searchId);

        assertEquals(result.get(0).getId(), searchId);
        assertEquals(result.get(0).getTitle(), "Title 1");
        assertEquals(result.get(0).getArtists().get(0).getId(), searchId);
        assertEquals(result.get(0).getArtists().get(0).getName(), "Kalle");


    }


}