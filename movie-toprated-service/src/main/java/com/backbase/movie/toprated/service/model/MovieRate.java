package com.backbase.movie.toprated.service.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Data
@Entity
@Table(name = "movie_rate")
public class MovieRate {

    @Id
    private String id;
    private String title;
    private Float rate;
    @Column(name = "rate_count")
    private Long rateCount;
    @Column(name = "box_office_value")
    private Long boxOfficeValue;
    private LocalDateTime modified;
}
