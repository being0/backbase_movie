package com.backbase.movie.toprated.service;

import com.backbase.movie.rate.service.event.RateEvent;
import com.backbase.movie.toprated.service.repository.MovieRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRateMessageConsumerTest {

    @Mock
    MovieRateRepository movieRateRepository;
    @Mock
    Clock clock;
    @InjectMocks
    UserRateMessageConsumer userRateMessageConsumer;

    void setupClock() {
        Clock fixedClock = Clock.fixed(Instant.parse("2022-03-31T22:15:30Z"), ZoneId.of("UTC"));
        doReturn(fixedClock.instant()).when(clock).instant();
        doReturn(fixedClock.getZone()).when(clock).getZone();
    }

    @Test
    void testConsume_createEvent() {
        // Given
        setupClock();
        RateEvent rateEvent = RateEvent.createEvent("1234", "mv1", (byte) 7);
        var now = LocalDateTime.now(clock);
        when(movieRateRepository.updateByNewRate("mv1", 7f, now)).thenReturn(1);

        // When
        userRateMessageConsumer.consume(rateEvent);

        // Then
        verify(movieRateRepository, times(1))
                .updateByNewRate(eq("mv1"), eq(Float.valueOf(7f)), eq(now));
    }

    @Test
    void testConsume_updateEvent() {
        // Given
        setupClock();
        RateEvent rateEvent = RateEvent.updateEvent("1234", "mv1", (byte) 8, (byte) 6);
        var now = LocalDateTime.now(clock);
        when(movieRateRepository.updateByDiffRate("mv1", 2f, now)).thenReturn(1);

        // When
        userRateMessageConsumer.consume(rateEvent);

        // Then
        verify(movieRateRepository, times(1))
                .updateByDiffRate(eq("mv1"), eq(Float.valueOf(2f)), eq(now));
    }

    @Test
    void testConsume_deleteEvent() {
        // Given
        setupClock();
        RateEvent rateEvent = RateEvent.deleteEvent("1234", "mv1", (byte) 8);
        var now = LocalDateTime.now(clock);
        when(movieRateRepository.updateDeleteRate("mv1", 8f, now)).thenReturn(1);

        // When
        userRateMessageConsumer.consume(rateEvent);

        // Then
        verify(movieRateRepository, times(1))
                .updateDeleteRate(eq("mv1"), eq(Float.valueOf(8f)), eq(now));
    }

}