package com.backbase.movie.rate.service;

import com.backbase.movie.rate.to.RateTo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Validated
public interface RateService {

    RateTo postRate(@NotNull @Size(min = 1, max = 20) String movieId, @Valid RateTo rateTo);

    RateTo getRate(@NotNull @Size(min = 1, max = 20) String movieId) throws RateNotFoundException;

    void deleteRate(@NotNull @Size(min = 1, max = 20) String movieId);
}
