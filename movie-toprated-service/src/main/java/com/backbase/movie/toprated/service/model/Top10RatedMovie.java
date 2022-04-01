package com.backbase.movie.toprated.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/30/2022
 */
@Data
@Entity
@Table(name = "top10_rated_movie")
@NoArgsConstructor
@AllArgsConstructor
public class Top10RatedMovie {
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
