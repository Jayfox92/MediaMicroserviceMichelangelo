package com.michelangelo.mediamicroservice.controllers;

import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.services.MediaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@WebMvcTest(MediaController.class)
public class MediaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MediaService mediaService;


    @BeforeEach
    public void setUp(){
    }

    @Test
    public void testGetMedia() throws Exception {
        long mediaId = 1;
        long userId = 1;
        Media media = new Media();
        media.setId(1L);

        when(mediaService.getMedia(mediaId,userId)).thenReturn(media);

        mockMvc.perform(get("/media/media/1/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMediaById() throws Exception {
        long mediaId = 1;
        Media media = new Media();
        media.setId(1L);

        when(mediaService.getMediaById(mediaId)).thenReturn(media);

        mockMvc.perform(get("/media/media/1"))
                .andExpect(status().isOk());
    }

}
