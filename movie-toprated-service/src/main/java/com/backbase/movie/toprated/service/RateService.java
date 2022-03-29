package com.backbase.movie.toprated.service;

import com.backbase.movie.toprated.to.CollectionResult;
import com.backbase.movie.toprated.to.MovieRateTo;
import org.springframework.validation.annotation.Validated;


/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Validated
public interface RateService {

    CollectionResult<MovieRateTo> getTop10RatedMovies();
}
