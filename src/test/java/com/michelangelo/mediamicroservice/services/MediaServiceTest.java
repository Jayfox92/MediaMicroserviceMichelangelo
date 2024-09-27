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
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
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

    private Media media1;
    private Media media2;
    private MediaUser mediaUser;

    private long mediaId = 1L;
    private long userId = 1L;

    @BeforeEach
    void setUp() {
        mediaUser = new MediaUser();
        mediaUser.setId(userId);

        media1 = new Media();
        media1.setId(1L);
        media1.setTitle("Media One");

        media2 = new Media();
        media2.setId(2L);
        media2.setTitle("Media Two");
    }

    // getMedia()
    @Test // Både media och användare hittas samt streaminghistorik uppdateras
    public void shouldReturnMediaAndUpdateStreamingHistoryWhenValidMediaIdAndUserIdIsProvided() {
        when(mediaRepositoryMock.findById(mediaId)).thenReturn(Optional.of(media1));
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
        when(mediaRepositoryMock.findById(mediaId)).thenReturn(Optional.of(media1));
        when(restTemplateMock.getForObject(eq("http://UserMicroservice/user/mediauser/getuser/" + userId), eq(MediaUser.class))).thenReturn(null);

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            mediaService.getMedia(mediaId, userId);
        });

        assertEquals("MediaUser not found with id : " + userId, thrown.getMessage());
        verify(mediaRepositoryMock, times(1)).findById(mediaId);
        verify(restTemplateMock, times(1)).getForObject(eq("http://UserMicroservice/user/mediauser/getuser/" + userId), eq(MediaUser.class));
        verify(restTemplateMock, times(0)).put(eq("http://UserMicroservice/user/streamhistory/increment/" + userId + "/" + mediaId), eq(Void.class));
    }


    @Test // Testa catch - MediaUser
    public void shouldThrowExceptionWhenUserServiceFailsInMediaUser(){
        when(mediaRepositoryMock.findById(mediaId)).thenReturn(Optional.of(media1));
        when(restTemplateMock.getForObject(eq("http://UserMicroservice/user/mediauser/getuser/" + userId), eq(MediaUser.class))).thenThrow(RestClientException.class);

        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {mediaService.getMedia(mediaId, userId);});

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, thrown.getStatusCode());
        verify(restTemplateMock, times(1)).getForObject(eq("http://UserMicroservice/user/mediauser/getuser/" + userId), eq(MediaUser.class));
        verify(restTemplateMock, never()).put(eq("http://UserMicroservice/user/streamhistory/increment/" + userId + "/" + mediaId), eq(Void.class));
    }

    @Test // Testa catch -
    public void shouldThrowExceptionWhenStreamHistoryFailsInMediaUser(){
        when(mediaRepositoryMock.findById(mediaId)).thenReturn(Optional.of(media1));
        when(restTemplateMock.getForObject(eq("http://UserMicroservice/user/mediauser/getuser/" + userId), eq(MediaUser.class))).thenReturn(mediaUser);
        doThrow(RestClientException.class).when(restTemplateMock).put(eq("http://UserMicroservice/user/streamhistory/increment/" + userId + "/" + mediaId), any());

        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {mediaService.getMedia(mediaId, userId);});

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, thrown.getStatusCode());
        verify(restTemplateMock, times(1)).getForObject(eq("http://UserMicroservice/user/mediauser/getuser/" + userId), eq(MediaUser.class));
        verify(restTemplateMock, times(1)).put(eq("http://UserMicroservice/user/streamhistory/increment/" + userId + "/" + mediaId), eq(Void.class));
    }



    // getMediaById()
    @Test
    public void shouldReturnMediaWhenValidIdIsProvided() {
        // Given
        when(mediaRepositoryMock.findById(mediaId)).thenReturn(Optional.of(media1));

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
    @Test
    public void shouldReturnMediaWhenValidGenreIdAndMediaTypeAreProvided() {
        long genreId = 1L;
        String mediaType = "Musik"; // Definiera media-typen, t.ex. "Musik"

        // Mockar repository-anropet så att det returnerar media1 och media2
        when(mediaRepositoryMock.findByGenres_IdAndTypeOfMedia_Type(genreId, mediaType))
                .thenReturn(Arrays.asList(media1, media2));

        // Anropar service-metoden
        List<Media> result = mediaService.findMediaByGenreId(genreId, mediaType);

        // Bekräftar att rätt media returnerades
        assertNotNull(result);
        assertEquals(2, result.size()); // Verifierar att två media returneras
        assertTrue(result.contains(media1));
        assertTrue(result.contains(media2));
    }


    // Nytt test för getAllMedia
    @Test
    public void shouldReturnAllMedia() {
        when(mediaRepositoryMock.findAll()).thenReturn(Arrays.asList(media1, media2));

        List<Media> result = mediaService.getAllMedia();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Media One", result.get(0).getTitle());
        assertEquals("Media Two", result.get(1).getTitle());
        verify(mediaRepositoryMock, times(1)).findAll();
    }
    @Test
    public void shouldReturnAllMediaByType() {
        String mediaType = "Musik"; // Definiera media-typen, t.ex. "Musik"

        // Mockar repository-anropet så att det returnerar media1 och media2
        when(mediaRepositoryMock.findByTypeOfMedia_Type(mediaType))
                .thenReturn(Arrays.asList(media1, media2));

        // Anropar service-metoden
        List<Media> result = mediaService.getAllMediaByType(mediaType);

        // Bekräftar att rätt media returnerades
        assertNotNull(result);
        assertEquals(2, result.size()); // Verifierar att två media returneras
        assertTrue(result.contains(media1));
        assertTrue(result.contains(media2));
        verify(mediaRepositoryMock, times(1)).findByTypeOfMedia_Type(mediaType);
    }
}
