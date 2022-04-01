package com.backbase.movie.toprated.to;

import lombok.Builder;
import lombok.Data;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/29/2022
 */
@Data
@Builder
public class MovieRateTo {

    private String id;
    private String title;
    private Float rate;
    private Long rateCount;
    private Long boxOfficeValue;
}
