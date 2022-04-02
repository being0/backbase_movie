package com.backbase.movie.toprated.controller;

import com.backbase.movie.toprated.service.Top10RatedMovieService;
import com.backbase.movie.toprated.to.CollectionResult;
import com.backbase.movie.toprated.to.MovieRateTo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class TopRatedControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    Top10RatedMovieService top10RatedMovieService;

    @Test
    void testGetRate() throws Exception {
        // Given
        MovieRateTo mv1 = MovieRateTo.builder().rate(8.6f).rateCount(111000L).id("123")
                .boxOfficeValue(101010L).title("12 Angry men").build();
        MovieRateTo mv2 = MovieRateTo.builder().rate(8.4f).rateCount(232332L).id("223")
                .boxOfficeValue(23232L).title("American Beauty").build();
        var result = new CollectionResult<>(List.of(mv1, mv2));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        String jsonBody = objectMapper.writeValueAsString(result);
        when(top10RatedMovieService.getTop10RatedMovies()).thenReturn(result);

        // When
        MvcResult mvcResult = this.mockMvc.perform(get("/movies/top10"))
                .andReturn();

        // Then
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(jsonBody, mvcResult.getResponse().getContentAsString());
    }


}