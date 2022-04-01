package com.backbase.movie.toprated.service;

import com.backbase.movie.toprated.service.model.Top10RatedMovie;
import com.backbase.movie.toprated.service.repository.Top10RatedMovieRepository;
import com.backbase.movie.toprated.to.CollectionResult;
import com.backbase.movie.toprated.to.MovieRateTo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This service provides top 10 movies ordered by user average rates and box office value,
 * Using an internal in-memory cache. The cache is updated periodically reading from top10_rated_movie view.
 *
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CachedTop10RatedMovieService implements Top10RatedMovieService {

    private final Top10RatedMovieRepository top10RatedMovieRepository;
    private CollectionResult<MovieRateTo> top10CachedMovies;

    /**
     * Returns top 10 rated cached movies.
     * Using in-memory cache helps to provide highly available and high performance service by a simple setup.
     *
     * @return top 10 rated movies
     */
    @Override
    public CollectionResult<MovieRateTo> getTop10RatedMovies() {

        // If cache not loaded yet, load it
        if (top10CachedMovies == null || top10CachedMovies.getResult().isEmpty()) {
            log.warn("Cache not loaded! Loading it manually");
            updateCache();
        }

        return top10CachedMovies;
    }

    /**
     * This method update in memory cache, reading data from top10_rated_movie materialized view.
     */
    @Scheduled(fixedRateString = "${my.top10.cache.refresh-interval-ms}", initialDelay = 2000)
    public void updateCache() {
        // Load top 10
        long t1 = System.currentTimeMillis();
        List<Top10RatedMovie> top10RatedMovies = top10RatedMovieRepository.findAll();
        log.info("Top 10 movies loaded in {}ms", System.currentTimeMillis() - t1);
        if (top10RatedMovies.size() != 10) log.warn("Size of top 10 is {}", top10RatedMovies.size());

        // Update cache
        top10CachedMovies = new CollectionResult<>(top10RatedMovies.stream().map(mv -> MovieRateTo.builder().rate(mv.getRate())
                .rateCount(mv.getRateCount()).boxOfficeValue(mv.getBoxOfficeValue())
                .id(mv.getId()).title(mv.getTitle()).build()).collect(Collectors.toUnmodifiableList()));
    }

}
