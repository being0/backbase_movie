package com.backbase.movie.toprated.to;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/25/2022
 */
@Data
public class RateTo {

    @Min(1)
    @Max(10)
    @NotNull
    private Byte rate;
}
