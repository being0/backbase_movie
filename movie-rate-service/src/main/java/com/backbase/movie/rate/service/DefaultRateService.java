package com.backbase.movie.rate.service;

import com.backbase.movie.rate.service.event.RateEvent;
import com.backbase.movie.rate.service.event.RateEventPublisher;
import com.backbase.movie.rate.service.model.RateKey;
import com.backbase.movie.rate.service.model.UserRate;
import com.backbase.movie.rate.service.repository.RateRepository;
import com.backbase.movie.rate.to.RateTo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Clock;
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
    private final RateEventPublisher rateEventPublisher;
    private final JwtHolder jwtHolder;
    private final Clock clock;

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
        UserRate userRate = UserRate.toCreate(userId, movieId, rateTo.getRate(), LocalDateTime.now(clock));

        // Get old rate to detect the type of event
        Optional<UserRate> prevRateOpt = rateRepository.findById(new RateKey(userId, movieId));

        // Save rate object into cassandra partitioned by userId
        rateRepository.save(userRate);

        // Publish event into the kafka
        if (prevRateOpt.isPresent()) {
            rateEventPublisher.publish(RateEvent.updateEvent(userId, movieId, userRate.getRate(), prevRateOpt.get().getRate()));
        } else {
            rateEventPublisher.publish(RateEvent.createEvent(userId, movieId, userRate.getRate()));
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
        Optional<UserRate> rateOpt = rateRepository.findById(new RateKey(userId, movieId));

        if (rateOpt.isEmpty()) throw new RateNotFoundException("There is not rate for movieId " + movieId);

        return new RateTo(rateOpt.get().getRate());
    }

    /**
     * Deletes user rate
     *
     * @param movieId movie id
     */
    @Override
    public void deleteRate(String movieId) {
        String userId = extractUserId();

        // Get previous rate
        Optional<UserRate> prevRateOpt = rateRepository.findById(new RateKey(userId, movieId));

        // Publish event
        if (prevRateOpt.isPresent()) {
            rateRepository.deleteById(new RateKey(userId, movieId));
            rateEventPublisher.publish(RateEvent.deleteEvent(userId, movieId, prevRateOpt.get().getRate()));
        }
    }

    private String extractUserId() {

        // extract user id, in the security configuration JWT has been used and JWT subject is user id
        String userId = jwtHolder.getJwt().getSubject();
        if (log.isDebugEnabled()) log.debug("UserId is {}", userId);

        return userId;
    }

}
