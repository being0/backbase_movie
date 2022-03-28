package com.backbase.movie.toprated.service.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Data
@Table(value = "movie_rate")
public class Rate {

    @PrimaryKey
    private RateKey key;
    private Byte rate;
    private LocalDateTime modified;
}
