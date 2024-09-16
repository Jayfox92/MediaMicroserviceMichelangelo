package com.michelangelo.mediamicroservice.services;

import com.michelangelo.mediamicroservice.VO.MediaUser;
import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.exceptions.ResourceNotFoundException;
import com.michelangelo.mediamicroservice.repositories.MediaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MediaServiceTest {

    @Mock
    private MediaRepository mediaRepositoryMock;

    @Mock
    private RestTemplate restTemplateMock;

    @InjectMocks
    private MediaService mediaService;

    private Media media;
    private MediaUser mediaUser;

    private long mediaId = 1L;
    private long userId = 1L;

    @BeforeEach
    void setUp() {
        mediaUser = new MediaUser();
        mediaUser.setId(1L);
        media = new Media();
        media.setId(this.mediaId);
    }

    // getMedia()
    @Test // Både media och användare hittas samt streaminghistorik uppdateras
    public void shouldReturnMediaAndUpdateStreamingHistoryWhenValidMediaIdAndUserIdIsProvided() {
        when(mediaRepositoryMock.findById(mediaId)).thenReturn(Optional.of(media));
        when(restTemplateMock.getForObject(eq("http://UserMicroservice/user/mediauser/getuser/" + userId), eq(MediaUser.class))).thenReturn(mediaUser);

        Media result = mediaService.getMedia(mediaId, userId);

        assertNotNull(result);
        assertEquals(mediaId, result.getId());
        verify(mediaRepositoryMock, times(1)).findById(mediaId);
        verify(restTemplateMock, times(1)).getForObject(eq("http://UserMicroservice/user/mediauser/getuser/" + userId), eq(MediaUser.class));
        verify(restTemplateMock, times(1)).put(eq("http://UserMicroservice/user/streamhistory/increment/" + userId + "/" + mediaId), eq(Void.class));
    }

    @Test // MediaId finns inte
    public void shouldThrowExceptionWhenInvalidMediaIdIsProvided() {
        when(mediaRepositoryMock.findById(mediaId)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            mediaService.getMedia(mediaId, userId);
        });

        assertEquals("Media not found with id : " + mediaId, thrown.getMessage());
        verify(mediaRepositoryMock, times(1)).findById(mediaId);
    }

    @Test // UserId finns inte
    public void shouldThrowExceptionWhenInvalidUserIdIsProvided() {
        when(mediaRepositoryMock.findById(mediaId)).thenReturn(Optional.of(media));
        when(restTemplateMock.getForObject(eq("http://UserMicroservice/user/mediauser/getuser/" + userId), eq(MediaUser.class))).thenReturn(null);

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            mediaService.getMedia(mediaId, userId);
        });

        assertEquals("MediaUser not found with id : " + userId, thrown.getMessage());
        verify(mediaRepositoryMock, times(1)).findById(mediaId);
        verify(restTemplateMock, times(1)).getForObject(eq("http://UserMicroservice/user/mediauser/getuser/" + userId), eq(MediaUser.class));
        verify(restTemplateMock, times(0)).put(eq("http://UserMicroservice/user/streamhistory/increment/" + userId + "/" + mediaId), eq(Void.class));
    }



    // getMediaById()
    @Test
    public void shouldReturnMediaWhenValidIdIsProvided() {
        // Given
        when(mediaRepositoryMock.findById(mediaId)).thenReturn(Optional.of(media));

        // When
        Media result = mediaService.getMediaById(mediaId);

        // Then
        assertNotNull(result);
        assertEquals(mediaId, result.getId());
        verify(mediaRepositoryMock, times(1)).findById(mediaId);
    }

    @Test
    public void shouldThrowExceptionWhenInvalidIdIsProvided() {
        // Given
        when(mediaRepositoryMock.findById(mediaId)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            mediaService.getMediaById(mediaId);
        });

        assertEquals("Media not found with id : " + mediaId, thrown.getMessage());
        verify(mediaRepositoryMock, times(1)).findById(mediaId);
    }


}
