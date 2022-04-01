package com.backbase.movie.bestpicture.controller;

import com.backbase.movie.bestpicture.BestpictureServiceApplication;
import com.backbase.movie.bestpicture.controller.error.ErrorTo;
import com.backbase.movie.bestpicture.service.BestPictureNomineeService;
import com.backbase.movie.bestpicture.service.MovieNotFoundException;
import com.backbase.movie.bestpicture.to.BestPictureNomineeTo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@SpringBootTest(classes = {BestpictureServiceApplication.class})
class BestPictureNomineeControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    BestPictureNomineeService bestPictureNomineeService;

    ObjectMapper om = new ObjectMapper();

    @Test
    void testGetBestPictureMovieByTitle() throws Exception {
        // Given
        var bestPictureNomineeTo = new BestPictureNomineeTo();
        bestPictureNomineeTo.setTitle("12 Angry men");
        bestPictureNomineeTo.setId("123abc");
        bestPictureNomineeTo.setWon(true);
        bestPictureNomineeTo.setAdditionalInfo("All characters exist in us!");
        bestPictureNomineeTo.setYear("1957 (30th)");

        om.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        String jsonBody = om.writeValueAsString(bestPictureNomineeTo);
        when(bestPictureNomineeService.getBestPictureMovieByTitle("12 Angry men")).thenReturn(bestPictureNomineeTo);

        // When
        MvcResult mvcResult = this.mockMvc.perform(get("/movies/bestpictures")
                        .param("title", "12 Angry men"))
                .andReturn();

        // Then
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(jsonBody, mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testGetBestPictureMovieByTitle_whenMovieNotFound() throws Exception {
        // Given
        when(bestPictureNomineeService.getBestPictureMovieByTitle("Unknown movie!"))
                .thenThrow(MovieNotFoundException.class);

        // When/Then
        this.mockMvc.perform(get("/movies/bestpictures")
                        .param("title", "Unknown movie!"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetBestPictureMovieByTitle_whenBlackTitle() throws Exception {
        // When/Then
        MvcResult mvcResult = this.mockMvc.perform(get("/movies/bestpictures")
                        .param("title", "    "))
                .andReturn();

        // Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
        assertEquals(om.writeValueAsString(new ErrorTo("title: must not be blank")), mvcResult.getResponse().getContentAsString());

    }

    @Test
    void testGetBestPictureMovieByTitle_whenTitleTooLong() throws Exception {
        // When/Then
        MvcResult mvcResult = this.mockMvc.perform(get("/movies/bestpictures")
                        .param("title", "123456789_123456789_123456789_123456789_123456789_123456789"))
                .andReturn();

        // Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
        assertEquals(om.writeValueAsString(new ErrorTo("title: size must be between 1 and 50")), mvcResult.getResponse().getContentAsString());

    }

}