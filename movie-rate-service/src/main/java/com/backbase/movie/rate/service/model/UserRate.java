package com.backbase.movie.rate.service.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Data
@Table(value = "movie_user_rate")
public class UserRate {

    @PrimaryKey
    private RateKey key;
    private Byte rate;
    private LocalDateTime modified;

    public static UserRate toCreate(String userId, String movieId, Byte rate) {
        UserRate userRate = new UserRate();
        userRate.setRate(rate);
        userRate.setKey(new RateKey(userId, movieId));
        userRate.setModified(LocalDateTime.now());

        return userRate;
    }
}
