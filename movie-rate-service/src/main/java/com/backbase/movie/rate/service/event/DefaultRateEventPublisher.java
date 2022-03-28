package com.backbase.movie.rate.service.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/28/2022
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class DefaultRateEventPublisher<T extends AbstractRateEvent> implements RateEventPublisher<T> {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publish(T rateEvent) {
        log.debug("Publishing rate {}", rateEvent);

        // TODO could event sourcing be a solution instead of this DB/event model?
        kafkaTemplate.send("movie_rate_topic", rateEvent);
    }
}
