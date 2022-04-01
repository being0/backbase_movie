package com.backbase.movie.bestpicture.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/25/2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestPictureNomineeTo {

    private String id;
    private String title;
    private String year;
    private String additionalInfo;
    private boolean won;
}
