package com.michelangelo.mediamicroservice.controllers;

import com.michelangelo.mediamicroservice.entities.Genre;
import com.michelangelo.mediamicroservice.services.GenreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

@WebMvcTest(GenreController.class)
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService genreService;

    @Test
    void getAllGenres() throws Exception {

        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(1L, "Rock"));
        genres.add(new Genre(2L, "Jazz"));

        when(genreService.getAll()).thenReturn(genres);


        mockMvc.perform(get("/media/genre/getall")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Rock"))
                .andExpect(jsonPath("$[1].name").value("Jazz"));
    }
}
