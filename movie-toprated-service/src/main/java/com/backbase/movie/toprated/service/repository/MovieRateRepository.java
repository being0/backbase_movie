package com.backbase.movie.toprated.service.repository;

import com.backbase.movie.toprated.service.model.MovieRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
public interface MovieRateRepository extends JpaRepository<MovieRate, String> {

    @Modifying
    @Transactional
    @Query("update MovieRate mr set mr.rate = ((mr.rateCount * mr.rate + :rate)/(mr.rateCount+1)) , mr.rateCount=mr.rateCount+1 where mr.id=:id")
    int updateByNewRate(@Param("id") String id, @Param("rate") Float rate);


}
