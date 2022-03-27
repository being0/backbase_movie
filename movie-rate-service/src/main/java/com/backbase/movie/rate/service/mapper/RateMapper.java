package com.backbase.movie.rate.service.mapper;

import com.backbase.movie.rate.service.model.Rate;
import com.backbase.movie.rate.to.RateTo;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Component
public class RateMapper implements BaseDtoDomainMapper<RateTo, Rate> {

    @Override
    public RateTo mapToDto(Rate rate) {
        if (rate == null) return null;

        var rateTo = new RateTo();
        rateTo.setRate(rate.getRate());

        return rateTo;
    }

    @Override
    public Rate mapToDomain(RateTo rateTo) {
        if (rateTo == null) return null;

        var rate = new Rate();
        rate.setRate(rateTo.getRate());

        return rate;
    }
}
