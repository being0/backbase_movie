package com.backbase.movie.rate.service.mapper;

import com.backbase.movie.rate.service.model.BestPictureNominee;
import com.backbase.movie.rate.to.BestPictureNomineeTo;
import com.backbase.movie.rate.service.mapper.BaseDtoDomainMapper;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Component
public class BestPictureMapper implements BaseDtoDomainMapper<BestPictureNomineeTo, BestPictureNominee> {

    @Override
    public BestPictureNomineeTo mapToDto(BestPictureNominee bestPictureNominee) {
        if (bestPictureNominee == null) return null;

        var bestPictureTo = new BestPictureNomineeTo();
        bestPictureTo.setId(bestPictureNominee.getId());
        bestPictureTo.setTitle(bestPictureNominee.getTitle());
        bestPictureTo.setAdditionalInfo(bestPictureNominee.getAdditionalInfo());
        bestPictureTo.setYear(bestPictureNominee.getYear());
        bestPictureTo.setWon(bestPictureNominee.isWon());

        return bestPictureTo;
    }

}
