package com.backbase.movie.rate.to;

import lombok.Data;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/25/2022
 */
@Data
public class BestPictureNomineeTo {

    private String id;
    private String title;
    private String year;
    private String additionalInfo;
    private boolean won;
}
