package com.backbase.movie.bestpicture.service;

import com.backbase.movie.bestpicture.service.mapper.BestPictureMapper;
import com.backbase.movie.bestpicture.service.model.BestPictureNominee;
import com.backbase.movie.bestpicture.service.repository.BestPictureNomineeRepository;
import com.backbase.movie.bestpicture.to.BestPictureNomineeTo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toUnmodifiableMap;

/**
 * This service loads best picture status of a movie by its title or throws MovieNotFoundException if it can not be found.
 * The service uses a local cache to make the service highly available with good performance
 *
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CachedBestPictureNomineeService implements BestPictureNomineeService {

    private final BestPictureNomineeRepository bestPictureNomineeRepository;
    private final BestPictureMapper bestPictureMapper;

    /**
     * Cache(map) of movie titles(lowercase) to BestPictureNomineeTo objects
     */
    private Map<String, BestPictureNomineeTo> bestPictureNomineeCache = Collections.emptyMap();

    /**
     * This method updates cache, The cache could be updated by a github action or scheduled job
     */
    @Scheduled(fixedRateString = "${my.best-picture-cache.refresh-interval}")
    @PostConstruct
    public void updateCache() {
        List<BestPictureNominee> bestPictures = bestPictureNomineeRepository.findAll();

        bestPictureNomineeCache = bestPictureMapper.mapToDtoList(bestPictures).stream()
                .collect(toUnmodifiableMap(to -> to.getTitle().trim().toLowerCase(), Function.identity()));

        log.info("Best picture cache updated! Cache size is {}", bestPictureNomineeCache.size());
    }

    /**
     * Get movie status for best picture nominee
     * @param title movie title
     * @return movie status
     * @throws MovieNotFoundException raise if movie is not in the list
     */
    @Override
    public BestPictureNomineeTo getBestPictureMovieByTitle(String title) throws MovieNotFoundException {

        // Get movie from cache by title
        BestPictureNomineeTo bestPictureNomineeTo = bestPictureNomineeCache.get(title.trim().toLowerCase());
        if (bestPictureNomineeTo == null) {
            throw new MovieNotFoundException("Movie with the following title is not in the best picture nominees list: " + title);
        }

        return bestPictureNomineeTo;
    }

}
