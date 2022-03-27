package com.backbase.movie.rate.service;

import com.backbase.movie.rate.service.mapper.RateMapper;
import com.backbase.movie.rate.service.model.Rate;
import com.backbase.movie.rate.service.model.RateKey;
import com.backbase.movie.rate.service.repository.RateRepository;
import com.backbase.movie.rate.to.RateTo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultRateService implements RateService {

    private final RateRepository rateRepository;
    private final RateMapper rateMapper;

    /**
     * Creates or updates a movie rate for user
     *
     * @param movieId movie id
     * @param rateTo rate object
     * @return rate object
     */
    @Override
    public RateTo postRate(String movieId, RateTo rateTo) {

        Rate rate = rateMapper.mapToDomain(rateTo);
        rate.setKey(new RateKey("121", movieId));

        rateRepository.save(rate);

        return rateTo;
    }

    /**
     * Gets movie rate for user
     *
     * @param movieId movie id
     * @return rate object
     * @throws RateNotFoundException if rate is not there throw this exception
     */
    @Override
    public RateTo getRate(String movieId) throws RateNotFoundException {

        // Find rate on DB
        Optional<Rate> rateOpt = rateRepository.findById(new RateKey("121", movieId));

        if (rateOpt.isEmpty()) throw new RateNotFoundException("There is not rate for movieId " + movieId);

        return rateMapper.mapToDto(rateOpt.get());
    }

    /**
     * Deletes user rate
     *
     * @param movieId movie id
     */
    @Override
    public void deleteRate(String movieId) {
        rateRepository.deleteById(new RateKey("121", movieId));
    }
}
