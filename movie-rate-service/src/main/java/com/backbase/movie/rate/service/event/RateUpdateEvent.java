package com.backbase.movie.rate.service.event;

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
public class RateUpdateEvent extends AbstractRateEvent {

    private Byte newRate;
    private Byte oldRate;

    @Builder
    public RateUpdateEvent(String userId, String movieId, Byte newRate, Byte oldRate) {
        setUserId(userId);
        setMovieId(movieId);
        this.newRate = newRate;
        this.oldRate = oldRate;
    }

}
