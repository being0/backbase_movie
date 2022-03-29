package com.backbase.movie.toprated.service;

import com.backbase.movie.toprated.service.repository.MovieRateRepository;
import com.backbase.movie.toprated.to.CollectionResult;
import com.backbase.movie.toprated.to.MovieRateTo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultRateService implements RateService {

    private final MovieRateRepository movieRateRepository;

    @Override
    public CollectionResult<MovieRateTo> getTop10RatedMovies() {
        return null;
    }
}
