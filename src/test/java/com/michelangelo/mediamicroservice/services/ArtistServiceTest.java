package com.michelangelo.mediamicroservice.services;

import com.michelangelo.mediamicroservice.entities.Album;
import com.michelangelo.mediamicroservice.entities.Artist;
import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.exceptions.ResourceNotFoundException;
import com.michelangelo.mediamicroservice.repositories.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ArtistServiceTest {

    private ArtistService artistService;
    private ArtistRepository artistRepositoryMock = mock(ArtistRepository.class);
    private Artist artist;

    @BeforeEach
    void setUp(){
        artistService = new ArtistService(artistRepositoryMock);
        artist = new Artist();
    }

    @Test
    public void shouldNotThrowIfArtistExistsAndReturnEmptyResultlist(){
        artist.setCreatedMedia(Collections.emptyList());
        when(artistRepositoryMock.findById(anyLong())).thenReturn(Optional.of(artist));
        List<Media> result = artistService.getMediaByArtist(1L);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldThrowIfNoArtistWithIdCanBeFound(){
        when(artistRepositoryMock.existsById(anyLong())).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, ()->artistService.getMediaByArtist(1L));
    }


    // Tests for: getAllAlbums
    @Test
    public void shouldReturnAllAlbumsWhenArtistExists(){
        long testId = 1L;
        List<Album> albumList = new ArrayList<>(List.of(new Album(), new Album(), new Album()));
        artist.setAlbums(albumList);

        when(artistRepositoryMock.findById(testId)).thenReturn(Optional.of(artist));

        assertEquals(albumList, artistService.getAllAlbums(testId));
    }

    @Test
    public void shouldThrowExceptionWhenNoArtistExists(){
        long testId = 1L;
        when(artistRepositoryMock.findById(testId)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, ()-> artistService.getAllAlbums(testId));
        assertEquals("Artist not found with id : 1", exception.getMessage());
    }


}