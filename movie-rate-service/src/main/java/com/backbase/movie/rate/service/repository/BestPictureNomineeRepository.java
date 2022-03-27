package com.backbase.movie.rate.service.repository;

import com.backbase.movie.rate.service.model.BestPictureNominee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
public interface BestPictureNomineeRepository extends JpaRepository<BestPictureNominee, String> {
}
