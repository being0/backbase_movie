package com.backbase.movie.bestpicture.service.mapper;

import com.backbase.movie.bestpicture.service.model.BestPictureNominee;
import com.backbase.movie.bestpicture.to.RateTo;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Component
public class BestPictureMapper implements BaseDtoDomainMapper<RateTo, BestPictureNominee> {

    @Override
    public RateTo mapToDto(BestPictureNominee bestPictureNominee) {
        if (bestPictureNominee == null) return null;

        var bestPictureTo = new RateTo();
        bestPictureTo.setId(bestPictureNominee.getId());
        bestPictureTo.setTitle(bestPictureNominee.getTitle());
        bestPictureTo.setAdditionalInfo(bestPictureNominee.getAdditionalInfo());
        bestPictureTo.setYear(bestPictureNominee.getYear());
        bestPictureTo.setWon(bestPictureNominee.isWon());

        return bestPictureTo;
    }

}
