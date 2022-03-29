package com.backbase.movie.rate.service.event;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/28/2022
 */
public interface RateEventPublisher {

    void publish(RateEvent rateEvent);
}
