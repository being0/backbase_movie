package com.backbase.movie.toprated.controller;

import com.backbase.movie.toprated.service.RateService;
import com.backbase.movie.toprated.to.CollectionResult;
import com.backbase.movie.toprated.to.MovieRateTo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/25/2022
 */
@RestController
@RequestMapping(value = "/movies")
@AllArgsConstructor
public class TopRatedController {

    private final RateService rateService;

    @GetMapping("/top10")
    public CollectionResult<MovieRateTo> getRate()  {

        return rateService.getTop10RatedMovies();
    }
}
