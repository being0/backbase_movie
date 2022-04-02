package com.backbase.movie.toprated.controller;

import com.backbase.movie.toprated.service.Top10RatedMovieService;
import com.backbase.movie.toprated.to.CollectionResult;
import com.backbase.movie.toprated.to.MovieRateTo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This service provides top 10 movies ordered by user average rates and movies box office values,
 *
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/25/2022
 */
@RestController
@RequestMapping(value = "/movies")
@AllArgsConstructor
@Slf4j
public class TopRatedController {

    private final Top10RatedMovieService rateService;

    @GetMapping("/top10")
    public CollectionResult<MovieRateTo> getTop10RatedMovies() {

        if (log.isDebugEnabled()) log.debug("GET /movies/top10 called.");

        return rateService.getTop10RatedMovies();
    }
}
