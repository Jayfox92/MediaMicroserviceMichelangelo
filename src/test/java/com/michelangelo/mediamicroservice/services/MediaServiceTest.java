package com.michelangelo.mediamicroservice.services;

import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.exceptions.ResourceNotFoundException;
import com.michelangelo.mediamicroservice.repositories.MediaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MediaServiceTest {

    @Mock
    private MediaRepository mediaRepository;

    @InjectMocks
    private MediaService mediaService;

    @Test
    public void shouldReturnMediaWhenValidIdIsProvided() {
        // Given
        Long mediaId = 1L;
        Media media = new Media();
        media.setId(mediaId);
        when(mediaRepository.findById(mediaId)).thenReturn(Optional.of(media));

        // When
        Media result = mediaService.getMediaById(mediaId,"username");

        // Then
        assertNotNull(result);
        assertEquals(mediaId, result.getId());
        verify(mediaRepository, times(1)).findById(mediaId);
    }

    @Test
    public void shouldThrowExceptionWhenInvalidIdIsProvided() {
        // Given
        Long mediaId = 2L;
        when(mediaRepository.findById(mediaId)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            mediaService.getMediaById(mediaId,"username");
        });

        assertEquals("Media not found with id : 2", thrown.getMessage());
        verify(mediaRepository, times(1)).findById(mediaId);
    }
}
