package com.backbase.movie.rate.service.repository;

import com.backbase.movie.rate.service.model.Rate;
import com.backbase.movie.rate.service.model.RateKey;
import org.springframework.data.repository.CrudRepository;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
public interface RateRepository extends CrudRepository<Rate, RateKey> {
}
