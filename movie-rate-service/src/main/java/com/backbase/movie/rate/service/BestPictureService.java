package com.backbase.movie.rate.service;

import com.backbase.movie.rate.to.BestPictureNomineeTo;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;


/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Validated
public interface BestPictureService {

    BestPictureNomineeTo getBestPictureMovieByTitle(@Size(min=1, max = 50) String title) throws com.backbase.movie.bestpicture.service.MovieNotFoundException;
}
