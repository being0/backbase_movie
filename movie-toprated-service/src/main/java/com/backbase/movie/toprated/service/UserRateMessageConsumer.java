package com.backbase.movie.toprated.service;

import com.backbase.movie.rate.service.event.RateEvent;
import com.backbase.movie.toprated.service.repository.MovieRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/29/2022
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class UserRateMessageConsumer {
    private final MovieRateRepository movieRateRepository;

    @KafkaListener(topics = "movie_rate_topic", groupId = "group1")
    public void consume(RateEvent rateEvent) {
        log.debug("Rate event {}", rateEvent);

        switch (rateEvent.getType()) {
            case create:
                movieRateRepository.updateByNewRate(rateEvent.getMovieId(), Float.valueOf(rateEvent.getRate()));
                break;
            case update:
                break;
            case delete:
                break;
            default:
                log.error("Uknown event {}", rateEvent);
        }
    }

}
