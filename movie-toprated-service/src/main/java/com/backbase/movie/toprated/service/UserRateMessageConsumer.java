package com.backbase.movie.toprated.service;

import com.backbase.movie.rate.service.event.RateEvent;
import com.backbase.movie.toprated.service.repository.MovieRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/29/2022
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class UserRateMessageConsumer {
    private final MovieRateRepository movieRateRepository;

    @KafkaListener(topics = "#{'${movie-rate-topic}'}", groupId = "#{'${spring.kafka.consumer.group-id}'}")
    public void consume(RateEvent rateEvent) {
        log.debug("Rate event {}", rateEvent);

        int updated;
        switch (rateEvent.getType()) {
            case create:
                updated = movieRateRepository.updateByNewRate(rateEvent.getMovieId(), Float.valueOf(rateEvent.getRate()), LocalDateTime.now());
                break;
            case update:
                updated = movieRateRepository.updateByDiffRate(rateEvent.getMovieId(),
                        (float) (rateEvent.getRate() - rateEvent.getPrevRate()), LocalDateTime.now());
                break;
            case delete:
                updated = movieRateRepository.updateDeleteRate(rateEvent.getMovieId(),
                        Float.valueOf(rateEvent.getPrevRate()), LocalDateTime.now());
                break;
            default:
                updated = 0;
                log.error("Uknown event {}", rateEvent);
                break;
        }

        if (updated == 0) log.error("Can't update movie rate for {}", rateEvent);
    }

}
