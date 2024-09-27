package com.michelangelo.mediamicroservice.controllers;

import com.michelangelo.mediamicroservice.services.ArtistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArtistController.class)
class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistService artistService;

    @Test
    void getAllAlbums() throws Exception {
        long artistId = 1L;


        when(artistService.getAllAlbums(artistId)).thenReturn(new ArrayList<>());


        mockMvc.perform(get("/media/artist/getallalbumsbyartist/{id}", artistId))
                .andExpect(status().isOk());
    }

    @Test
    void getMediaByArtist() throws Exception {
        long artistId = 1L;


        when(artistService.getMediaByArtist(artistId)).thenReturn(new ArrayList<>());


        mockMvc.perform(get("/media/artist/getmediabyartist/{id}", artistId))
                .andExpect(status().isOk());
    }
}
