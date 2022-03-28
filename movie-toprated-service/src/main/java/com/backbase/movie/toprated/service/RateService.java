package com.backbase.movie.toprated.service;

import com.backbase.movie.toprated.to.RateTo;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Validated
public interface RateService {

    RateTo postRate(@NotNull @Size(min = 1, max = 20) String movieId, RateTo rateTo);

    RateTo getRate(@NotNull @Size(min = 1, max = 20) String movieId) throws RateNotFoundException;

    void deleteRate(String movieId);
}
