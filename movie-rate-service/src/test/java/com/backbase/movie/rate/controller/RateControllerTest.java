package com.backbase.movie.rate.controller;

import com.backbase.movie.rate.RateServiceApplication;
import com.backbase.movie.rate.service.RateNotFoundException;
import com.backbase.movie.rate.service.RateService;
import com.backbase.movie.rate.to.RateTo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.validation.ConstraintViolationException;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@SpringBootTest
class RateControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    RateService rateService;

    ObjectMapper om = new ObjectMapper();

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    public void givenNoToken_whenPostRate_thenUnauthorized() throws Exception {

        mockMvc.perform(post("/movies/121/users/rate"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void givenNoToken_whenGetRate_thenUnauthorized() throws Exception {

        mockMvc.perform(get("/movies/121/users/rate"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void givenNoToken_whenDeleteRate_thenUnauthorized() throws Exception {

        mockMvc.perform(delete("/movies/121/users/rate"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testPostRate() throws Exception {
        // Given
        RateTo rateTo = new RateTo((byte) 8);
        String jsonBody = om.writeValueAsString(rateTo);
        when(rateService.postRate("121", rateTo)).thenReturn(rateTo);

        // When
        MvcResult mvcResult = this.mockMvc.perform(post("/movies/121/users/rate")
                        .content(jsonBody).contentType(APPLICATION_JSON_UTF8).with(jwt()))
                .andReturn();

        // Then
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(jsonBody, mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testGetRate() throws Exception {
        // Given
        RateTo rateTo = new RateTo((byte) 7);
        String jsonBody = om.writeValueAsString(rateTo);
        when(rateService.getRate("abcd123")).thenReturn(rateTo);

        // When
        MvcResult mvcResult = this.mockMvc.perform(get("/movies/abcd123/users/rate")
                        .with(jwt()))
                .andReturn();

        // Then
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(jsonBody, mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testGetRate_whenRateNotFound() throws Exception {
        // Given
        when(rateService.getRate("abcd123")).thenThrow(RateNotFoundException.class);

        // When/Then
        this.mockMvc.perform(get("/movies/abcd123/users/rate").with(jwt()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testPostRate_whenRateNotValid() throws Exception {
        // Given
        RateTo rateTo = new RateTo((byte) 11);
        String jsonBody = om.writeValueAsString(rateTo);
        when(rateService.postRate("abcd123",rateTo)).thenThrow(ConstraintViolationException.class);


        // When/Then
        MvcResult mvcResult = this.mockMvc.perform(post("/movies/abcd123/users/rate")
                        .content(jsonBody).contentType(APPLICATION_JSON_UTF8).with(jwt()))
                .andReturn();

        // Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    void testDeleteRate() throws Exception {

        // When
        MvcResult mvcResult = this.mockMvc.perform(delete("/movies/abcd123/users/rate")
                        .with(jwt()))
                .andReturn();

        // Then
        assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());
        verify(rateService, times(1)).deleteRate(eq("abcd123"));
    }


}