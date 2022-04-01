package com.backbase.movie.rate.service;

import com.backbase.movie.rate.service.event.RateEvent;
import com.backbase.movie.rate.service.event.RateEventPublisher;
import com.backbase.movie.rate.service.model.RateKey;
import com.backbase.movie.rate.service.model.UserRate;
import com.backbase.movie.rate.service.repository.RateRepository;
import com.backbase.movie.rate.to.RateTo;
import org.apache.kafka.common.metrics.stats.Rate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultRateServiceTest {

    @Mock
    RateRepository rateRepository;
    @Mock
    RateEventPublisher rateEventPublisher;
    @Mock
    JwtHolder jwtHolder;
    @Mock
    Jwt jwt;
    @Mock
    Clock clock;
    @InjectMocks
    DefaultRateService defaultRateService;

    String userId = "user_id_1";

    @BeforeEach
    void setupJwt() {
        when(jwtHolder.getJwt()).thenReturn(jwt);
        when(jwtHolder.getJwt().getSubject()).thenReturn(userId);
    }

    void setupClock() {
        Clock fixedClock = Clock.fixed(Instant.parse("2022-03-31T22:15:30Z"), ZoneId.of("UTC"));
        doReturn(fixedClock.instant()).when(clock).instant();
        doReturn(fixedClock.getZone()).when(clock).getZone();
    }

    @Test
    void testPostRate_new() {
        // Given
        setupClock();
        String movieId = "movie_id_1";
        when(rateRepository.findById(new RateKey(userId, movieId))).thenReturn(Optional.empty());

        // When
        defaultRateService.postRate(movieId, new RateTo((byte) 8));

        // Then
        verify(rateRepository, times(1)).save(eq(UserRate.toCreate(userId, movieId, (byte) 8, LocalDateTime.now(clock))));
        verify(rateEventPublisher, times(1)).publish(eq(RateEvent.createEvent(userId, movieId, (byte) 8)));
    }

    @Test
    void testPostRate_update() {
        // Given
        setupClock();
        String movieId = "movie_id_1";
        UserRate userRate = UserRate.toCreate(userId, movieId, (byte) 6, LocalDateTime.now(clock));
        when(rateRepository.findById(new RateKey(userId, movieId))).thenReturn(Optional.of(userRate));

        // When
        defaultRateService.postRate(movieId, new RateTo((byte) 8));

        // Then
        verify(rateRepository, times(1)).save(eq(UserRate.toCreate(userId, movieId, (byte) 8, LocalDateTime.now(clock))));
        verify(rateEventPublisher, times(1)).publish(eq(RateEvent.updateEvent(userId, movieId, (byte) 8, (byte) 6)));
    }

    @Test
    void testGetRate_notFound() {
        // Given
        String movieId = "movie_id_1";
        when(rateRepository.findById(new RateKey(userId, movieId))).thenReturn(Optional.empty());

        // When/Then
        assertThrows(RateNotFoundException.class, () -> defaultRateService.getRate(movieId));
    }

    @Test
    void testGetRate() throws RateNotFoundException {
        // Given
        setupClock();
        String movieId = "movie_id_1";
        UserRate userRate = UserRate.toCreate(userId, movieId, (byte) 6, LocalDateTime.now(clock));
        when(rateRepository.findById(new RateKey(userId, movieId))).thenReturn(Optional.of(userRate));

        // When
        RateTo foundRateTo = defaultRateService.getRate(movieId);

        // Then
        assertEquals(new RateTo(userRate.getRate()), foundRateTo);
    }

    @Test
    void testDeleteRate_wheNotThere() {
        // Given
        String movieId = "movie_id_1";
        when(rateRepository.findById(new RateKey(userId, movieId))).thenReturn(Optional.empty());

        // When
        defaultRateService.deleteRate(movieId);

        // Then
        verify(rateRepository, times(0)).deleteById(any());
    }

    @Test
    void testDeleteRate() {
        // Given
        setupClock();
        String movieId = "movie_id_1";
        UserRate userRate = UserRate.toCreate(userId, movieId, (byte) 6, LocalDateTime.now(clock));
        when(rateRepository.findById(new RateKey(userId, movieId))).thenReturn(Optional.of(userRate));

        // When
        defaultRateService.deleteRate(movieId);

        // Then
        verify(rateRepository, times(1)).deleteById(eq(new RateKey(userId, movieId)));
        verify(rateEventPublisher, times(1)).publish(eq(RateEvent.deleteEvent(userId, movieId, (byte) 6)));
    }


}