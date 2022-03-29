package com.backbase.movie.rate.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/25/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateTo {

    @Min(1)
    @Max(10)
    @NotNull
    private Byte rate;
}
