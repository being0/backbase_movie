package com.backbase.movie.rate.service.event;

import lombok.Data;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/28/2022
 */
@Data
public abstract class AbstractRateEvent {

    protected String userId;
    protected String movieId;
}
