package com.michelangelo.mediamicroservice.repositories;

import com.michelangelo.mediamicroservice.entities.Album;
import com.michelangelo.mediamicroservice.entities.Artist;
import com.michelangelo.mediamicroservice.entities.Media;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = "/schema.sql")
class ArtistRepositoryTest {
    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private AlbumRepository albumRepository;

    private Artist artist1;
    private final String artistName = "Test Artist";

    private Artist artist2;

    @BeforeEach
    void setUp() {
        artist1 = new Artist();
        artist1.setName(artistName);

        artist2 = new Artist();
        artist2.setName(artistName); // Enbart för att name är not null
    }

    // findAllMediaByArtistId()
    @Test
    void shouldReturnMediaWithArtistId() {
        String mediaTitle = "Test Title";
        String mediaURL = "Test URL";

        Media media1 = new Media();
        media1.setTitle(mediaTitle);
        media1.setUrl(mediaURL);

        List<Artist> artistList = new ArrayList<>();
        artistList.add(artist1);
        media1.setArtists(artistList);

        List<Media> mediaList = new ArrayList<>();
        mediaList.add(media1);

        artist1.setCreatedMedia(mediaList);

        artistRepository.save(artist1);
        mediaRepository.save(media1);

        List<Media> result = artistRepository.findAllMediaByArtistId(artist1.getId());

        assertEquals(artist1.getId(), result.get(0).getArtists().get(0).getId());
        assertEquals(artistName, result.get(0).getArtists().get(0).getName());
        assertEquals(mediaURL, result.get(0).getUrl());
        assertEquals(mediaTitle, result.get(0).getTitle());
    }

    @Test
    void shouldReturnEmptyListOfMediaWhenNoMediaExistForArtistId() {
        artistRepository.save(artist2); // Spara artist utan media

        List<Media> albumsReturned = artistRepository.findAllMediaByArtistId(artist2.getId());

        assertEquals(0, albumsReturned.size());
        assertTrue(albumsReturned.isEmpty());
    }


    // findAlbumsById()
    @Test
    void shouldReturnListOfAlbumsWhenAlbumsExistsForArtistId() {
        String albumTitle1 = "Test Title 1";
        Album album1 = new Album();
        album1.setTitle(albumTitle1);
        List<Album> albumList = new ArrayList<>(List.of(album1));

        artist1.setAlbums(albumList);

        album1.setArtist(new ArrayList<>());
        album1.getArtists().add(artist1);

        albumRepository.save(album1);
        artistRepository.save(artist1);

        List<Album> albumsReturned = artistRepository.findAlbumsById(artist1.getId());

        assertEquals(artist1.getId(), albumsReturned.get(0).getArtists().get(0).getId());
        assertEquals(artist1.getName(), albumsReturned.get(0).getArtists().get(0).getName());
        assertEquals(albumList.size(), albumsReturned.size());
        assertEquals(albumList.get(0).getId(), albumsReturned.get(0).getId());
        assertEquals(albumTitle1, albumsReturned.get(0).getTitle());
    }


    @Test
    void shouldReturnEmptyListOfAlbumsWhenNoAlbumsExistForArtistId() {
        artistRepository.save(artist2); // Spara artist utan album

        List<Album> albumsReturned = artistRepository.findAlbumsById(artist2.getId());

        assertEquals(0, albumsReturned.size());
        assertTrue(albumsReturned.isEmpty());
    }
}
