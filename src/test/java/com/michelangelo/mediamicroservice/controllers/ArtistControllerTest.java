package com.michelangelo.mediamicroservice.controllers;

import com.michelangelo.mediamicroservice.entities.Album;
import com.michelangelo.mediamicroservice.entities.Artist;
import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.services.ArtistService;
import com.michelangelo.mediamicroservice.services.MediaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(ArtistController.class)
class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ArtistService artistService;


    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllAlbums() throws Exception{
        long artistId = 1;


        when(artistService.getAllAlbums(artistId)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/v1/artist/getallalbumsbyartist/1"))
                .andExpect(status().isOk());
    }
    @RequestMapping("/v1/artist")

    @Test
    void getMediaByArtist() throws Exception {
        long artistId = 1;

        when(artistService.getMediaByArtist(artistId)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/v1/artist/getmediabyartist/1"))
                .andExpect(status().isOk());
    }


}