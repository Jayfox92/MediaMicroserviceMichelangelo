package com.michelangelo.mediamicroservice.services;

import com.michelangelo.mediamicroservice.entities.Artist;
import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.exceptions.ResourceNotFoundException;
import com.michelangelo.mediamicroservice.repositories.ArtistRepository;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ArtistServiceTest {

    private ArtistService artistService;

    @Test
    public void shouldNotThrowIfArtistExistsAndReturnEmptyResultlist(){
        ArtistRepository mockArtistRepo = mock(ArtistRepository.class);
        Artist artist = new Artist();
        artist.setCreatedMedia(Collections.emptyList());
        artistService = new ArtistService(mockArtistRepo);
        when(mockArtistRepo.findById(anyLong())).thenReturn(Optional.of(artist));
        List<Media> result = artistService.getMediaByArtist(1L);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldThrowIfNoArtistWithIdCanBeFound(){
        ArtistRepository mockArtistRepo = mock(ArtistRepository.class);
        artistService = new ArtistService(mockArtistRepo);
        when(mockArtistRepo.existsById(anyLong())).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, ()->artistService.getMediaByArtist(1L));

    }


}