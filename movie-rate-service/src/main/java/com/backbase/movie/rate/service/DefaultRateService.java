package com.backbase.movie.rate.service;

import com.backbase.movie.rate.service.event.AbstractRateEvent;
import com.backbase.movie.rate.service.event.RateCreateEvent;
import com.backbase.movie.rate.service.event.RateEventPublisher;
import com.backbase.movie.rate.service.event.RateUpdateEvent;
import com.backbase.movie.rate.service.mapper.RateMapper;
import com.backbase.movie.rate.service.model.Rate;
import com.backbase.movie.rate.service.model.RateKey;
import com.backbase.movie.rate.service.repository.RateRepository;
import com.backbase.movie.rate.to.RateTo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final RateEventPublisher<AbstractRateEvent> rateEventPublisher;
    private final JwtHolder jwtHolder;

    /**
     * Creates or updates a movie rate for user
     *
     * @param movieId movie id
     * @param rateTo  rate object
     * @return rate object
     */
    @Override
    public RateTo postRate(String movieId, RateTo rateTo) {
        String userId = extractUserId();

        // Prepare rate to save
        Rate rate = rateMapper.mapToDomain(rateTo);
        rate.setKey(new RateKey(userId, movieId));
        rate.setModified(LocalDateTime.now());

        // Get old rate to detect the type of event
        Optional<Rate> oldRateOpt = rateRepository.findById(new RateKey(userId, movieId));

        // Save rate object
        rateRepository.save(rate);

        // Publish event
        if (oldRateOpt.isPresent()) {
            rateEventPublisher.publish(new RateUpdateEvent(userId, movieId, rate.getRate(), oldRateOpt.get().getRate()));
        } else {
            rateEventPublisher.publish(new RateCreateEvent(userId, movieId, rate.getRate()));
        }

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
        String userId = extractUserId();

        // Find rate on DB
        Optional<Rate> rateOpt = rateRepository.findById(new RateKey(userId, movieId));

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
        String userId = extractUserId();

        // Get old rate
        Optional<Rate> oldRateOpt = rateRepository.findById(new RateKey(userId, movieId));

        // Publish event
        if (oldRateOpt.isPresent()) {
            rateRepository.deleteById(new RateKey(userId, movieId));
            rateEventPublisher.publish(new RateCreateEvent(userId, movieId, oldRateOpt.get().getRate()));
        }
    }

    private String extractUserId() {

        // extract user id, in the security configuration JWT has been used and JWT subject is user id
        return jwtHolder.getJwt().getSubject();
    }

}
