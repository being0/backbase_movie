package com.backbase.movie.rate.service.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/28/2022
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RateCreateEvent extends AbstractRateEvent {

    private Byte rate;

    @Builder
    public RateCreateEvent(String userId, String movieId, Byte rate) {
        setUserId(userId);
        setMovieId(movieId);
        this.rate = rate;
    }
}
