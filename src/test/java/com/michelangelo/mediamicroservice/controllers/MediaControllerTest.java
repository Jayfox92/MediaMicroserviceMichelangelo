package com.michelangelo.mediamicroservice.controllers;

import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.services.MediaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@WebMvcTest(MediaController.class)
public class MediaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MediaService mediaService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetMedia() throws Exception {
        long mediaId = 1;
        long userId = 1;
        Media media = new Media();
        media.setId(1L);
        media.setTitle("Media1");

        when(mediaService.getMedia(mediaId, userId)).thenReturn(media);

        mockMvc.perform(get("/media/media/1/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Media1"));
    }

    @Test
    public void testGetMediaById() throws Exception {
        long mediaId = 1;
        Media media = new Media();
        media.setId(1L);
        media.setTitle("Media1");

        when(mediaService.getMediaById(mediaId)).thenReturn(media);

        mockMvc.perform(get("/media/media/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Media1"));
    }


    @Test
    void testGetAllMedia() throws Exception {
        when(mediaService.getAllMedia()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/media/media/getall"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMediaByGenreId() throws Exception {
        long genreId = 1;
        String mediaType = "musik";
        List<Media> mediaList = new ArrayList<>();
        Media media1 = new Media();
        media1.setId(1L);
        media1.setTitle("Media1");
        Media media2 = new Media();
        media2.setId(2L);
        media2.setTitle("Media2");

        mediaList.add(media1);
        mediaList.add(media2);

        when(mediaService.findMediaByGenreId(genreId, mediaType)).thenReturn(mediaList);

        mockMvc.perform(get("/media/media/genre/1/musik"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("Media1"))
                .andExpect(jsonPath("$[1].title").value("Media2"));
    }

    @Test
    public void testGetAllMediaByType() throws Exception {
        String mediaType = "podcast";
        List<Media> mediaList = new ArrayList<>();
        Media media1 = new Media();
        media1.setId(1L);
        media1.setTitle("Podcast1");
        Media media2 = new Media();
        media2.setId(2L);
        media2.setTitle("Podcast2");

        mediaList.add(media1);
        mediaList.add(media2);

        when(mediaService.getAllMediaByType(mediaType)).thenReturn(mediaList);

        mockMvc.perform(get("/media/media/getallbymediatype/podcast"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("Podcast1"))
                .andExpect(jsonPath("$[1].title").value("Podcast2"));
    }
}
