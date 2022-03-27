package com.backbase.movie.bestpicture.service;

import com.backbase.movie.bestpicture.to.RateTo;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;


/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Validated
public interface BestPictureService {

    RateTo getBestPictureMovieByTitle(@Size(min=1, max = 50) String title) throws MovieNotFoundException;
}
