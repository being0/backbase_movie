package com.backbase.movie.toprated.service;

import com.backbase.movie.toprated.service.model.Top10RatedMovie;
import com.backbase.movie.toprated.service.repository.Top10RatedMovieRepository;
import com.backbase.movie.toprated.to.CollectionResult;
import com.backbase.movie.toprated.to.MovieRateTo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CachedTop10RatedMovieServiceTest {

    @Mock
    Top10RatedMovieRepository top10RatedMovieRepository;
    @InjectMocks
    CachedTop10RatedMovieService cachedTop10RatedMovieService;

    @Test
    void testGetTop10RatedMovies() {
        // Given
        var movie1 = new Top10RatedMovie("1", "12 Angry men", 9.6f, 2123L, 12189898L, LocalDateTime.now());
        var movie2 = new Top10RatedMovie("2", "American beauty", 9.4f, 23234L, 234423L, LocalDateTime.now());
        var movie3 = new Top10RatedMovie("3", "A Separation", 8.8f, 3232L, 943L, LocalDateTime.now());
        var movies = List.of(movie1, movie2, movie3);
        when(top10RatedMovieRepository.findAll()).thenReturn(movies);

        // When
        CollectionResult<MovieRateTo> top10 = cachedTop10RatedMovieService.getTop10RatedMovies();

        // Then
        var top10Expected = new CollectionResult<>(movies.stream().map(mv -> MovieRateTo.builder().rate(mv.getRate())
                .rateCount(mv.getRateCount()).boxOfficeValue(mv.getBoxOfficeValue())
                .id(mv.getId()).title(mv.getTitle()).build()).collect(Collectors.toUnmodifiableList()));

        assertEquals(top10Expected, top10);
    }
}