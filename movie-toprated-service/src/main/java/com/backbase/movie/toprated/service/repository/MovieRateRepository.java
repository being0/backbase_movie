package com.backbase.movie.toprated.service.repository;

import com.backbase.movie.toprated.service.model.MovieRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
public interface MovieRateRepository extends JpaRepository<MovieRate, String> {

    @Modifying
    @Transactional
    @Query("update MovieRate mr set mr.rate = (mr.rateCount * mr.rate + :rate)/(mr.rateCount+1) , " +
            "mr.rateCount=mr.rateCount+1, mr.modified=:now " +
            "where mr.id=:id")
    int updateByNewRate(@Param("id") String id, @Param("rate") Float rate, @Param("now") LocalDateTime now);

    @Modifying
    @Transactional
    @Query("update MovieRate mr set mr.rate = (mr.rateCount * mr.rate + :rateDiff)/mr.rateCount , " +
            "mr.modified=:now " +
            "where mr.id=:id")
    int updateByDiffRate(@Param("id") String id, @Param("rateDiff") Float rateDiff, @Param("now") LocalDateTime now);

    @Modifying
    @Transactional
    @Query("update MovieRate mr set mr.rate = (mr.rateCount * mr.rate - :prevRate)/(mr.rateCount-1) , " +
            "mr.rateCount=mr.rateCount-1, mr.modified=:now " +
            "where mr.id=:id")
    int updateDeleteRate(@Param("id") String id, @Param("prevRate") Float prevRate, @Param("now") LocalDateTime now);

}
