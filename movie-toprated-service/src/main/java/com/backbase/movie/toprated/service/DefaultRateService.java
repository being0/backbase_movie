package com.backbase.movie.toprated.service;

import com.backbase.movie.toprated.service.mapper.RateMapper;
import com.backbase.movie.toprated.service.model.Rate;
import com.backbase.movie.toprated.service.model.RateKey;
import com.backbase.movie.toprated.service.repository.RateRepository;
import com.backbase.movie.toprated.to.RateTo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
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
    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Creates or updates a movie rate for user
     *
     * @param movieId movie id
     * @param rateTo rate object
     * @return rate object
     */
    @Override
    public RateTo postRate(String movieId, RateTo rateTo) {

        // Prepare rate to save
        Rate rate = rateMapper.mapToDomain(rateTo);
        rate.setKey(new RateKey("121", movieId));
        rate.setModified(LocalDateTime.now());

        // Save rate object
        rateRepository.save(rate);

        kafkaTemplate.send("movie_rate_topic", rateTo);

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

    @KafkaListener(topics = "movie_rate_topic", groupId = "group1")
    public void consume(com.backbase.movie.rate.to.RateTo rateTo)
    {
        log.info("User created {}", rateTo);
    }
}
