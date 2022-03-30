package com.backbase.movie.toprated.service.repository;

import com.backbase.movie.toprated.service.model.Top10RatedMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/30/2022
 */
public interface Top10RatedMovieRepository extends JpaRepository<Top10RatedMovie, String> {

    @Modifying
    @Transactional
    @Query(value = "REFRESH MATERIALIZED VIEW top10_rated_movie", nativeQuery = true)
    void refreshMaterializedView();
}
