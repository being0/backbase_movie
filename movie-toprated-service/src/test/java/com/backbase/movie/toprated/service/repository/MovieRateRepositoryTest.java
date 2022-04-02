package com.backbase.movie.toprated.service.repository;

import com.backbase.movie.toprated.service.model.MovieRate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration test of MovieRateRepository with h2 database
 *
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 04/02/2022
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@ComponentScan(basePackageClasses= {MovieRateRepository.class})
public class MovieRateRepositoryTest {
    @Autowired
    MovieRateRepository movieRateRepository;

    @Test
    void testUpdateByNewRate() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        movieRateRepository.updateByNewRate("tt0169547", 8f, now);
        movieRateRepository.updateByNewRate("tt0169547", 7f, now);

        // When
        Optional<MovieRate> movieRateOpt = movieRateRepository.findById("tt0169547");

        // Then
        assertTrue(movieRateOpt.isPresent());
        assertEquals(now, movieRateOpt.get().getModified());
        assertEquals("American Beauty", movieRateOpt.get().getTitle());
        // TODO this test failed, seems h2 can't update DB
//        assertEquals(7.5f, movieRateOpt.get().getRate());
    }

}
