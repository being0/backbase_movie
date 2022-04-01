package com.backbase.movie.bestpicture.service;

import com.backbase.movie.bestpicture.to.BestPictureNomineeTo;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Validated
public interface BestPictureNomineeService {

    /**
     * Get movie status for best picture nominee
     * @param title movie title
     * @return movie status
     * @throws MovieNotFoundException raise if movie is not in the list
     */
    BestPictureNomineeTo getBestPictureMovieByTitle(@NotEmpty @Size(min=1, max = 50) String title) throws MovieNotFoundException;
}
