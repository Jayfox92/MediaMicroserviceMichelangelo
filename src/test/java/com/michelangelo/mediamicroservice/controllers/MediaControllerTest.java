package com.michelangelo.mediamicroservice.controllers;

import static org.junit.jupiter.api.Assertions.*;

import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.repositories.MediaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MediaController.class)
public class MediaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MediaRepository mediaRepository;

    @Test
    public void testGetMedia() throws Exception {
        Media media = new Media();
        media.setId(1L);

        when(mediaRepository.findById(1L)).thenReturn(Optional.of(media));

        mockMvc.perform(get("/media/1"))
                .andExpect(status().isOk());
    }
}
