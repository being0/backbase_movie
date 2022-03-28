package com.backbase.movie.toprated.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@PrimaryKeyClass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateKey implements Serializable {

    // Partition based on userId, it helps to load all user rates by using the same partition
    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String userId;

    @PrimaryKeyColumn(name = "movie_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private String movieId;

}
