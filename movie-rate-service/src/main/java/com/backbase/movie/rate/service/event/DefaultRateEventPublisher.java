package com.backbase.movie.rate.service.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/28/2022
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class DefaultRateEventPublisher implements RateEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${my.movie-rate.topic}")
    private String kafkaTopic;

    @Override
    public void publish(RateEvent rateEvent) {
        log.debug("Publishing rate {}", rateEvent);

        // TODO could event sourcing be a solution instead of this DB/event model?
        kafkaTemplate.send(kafkaTopic, rateEvent);
    }
}
