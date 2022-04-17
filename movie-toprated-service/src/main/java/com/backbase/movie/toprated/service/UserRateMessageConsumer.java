package com.backbase.movie.toprated.service;

import com.backbase.movie.rate.service.event.RateEvent;
import com.backbase.movie.toprated.service.repository.MovieRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;

/**
 * This component reads kafka RateEvents and create/update/delete rates in the database
 *
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/29/2022
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class UserRateMessageConsumer {
    private final MovieRateRepository movieRateRepository;
    private final Clock clock;

    @KafkaListener(topics = "#{'${my.movie-rate.topic:movie_rate_topic}'}", groupId = "#{'${spring.kafka.consumer.group-id:group1}'}")
    public void consume(RateEvent rateEvent) {
        if(log.isDebugEnabled()) log.debug("Rate event {}", rateEvent);

        // To optimize performance we should read in batch(like 100) and combine and calculate and then send one update query to DB
        // For simplicity it only updates for one message.
        int updated;
        switch (rateEvent.getType()) {
            case CREATE:
                updated = movieRateRepository.updateByNewRate(rateEvent.getMovieId(), Float.valueOf(rateEvent.getRate()), LocalDateTime.now(clock));
                break;
            case UPDATE:
                updated = movieRateRepository.updateByDiffRate(rateEvent.getMovieId(),
                        (float) (rateEvent.getRate() - rateEvent.getPrevRate()), LocalDateTime.now(clock));
                break;
            case DELETE:
                updated = movieRateRepository.updateDeleteRate(rateEvent.getMovieId(),
                        Float.valueOf(rateEvent.getPrevRate()), LocalDateTime.now(clock));
                break;
            default:
                updated = 0;
                log.error("Uknown event {}", rateEvent);
                break;
        }

        if (updated == 0) log.error("Can't update movie rate for {}", rateEvent);
    }

}
