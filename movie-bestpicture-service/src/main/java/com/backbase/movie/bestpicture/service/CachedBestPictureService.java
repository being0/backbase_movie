package com.backbase.movie.bestpicture.service;

import com.backbase.movie.bestpicture.service.mapper.BestPictureMapper;
import com.backbase.movie.bestpicture.service.model.BestPictureNominee;
import com.backbase.movie.bestpicture.service.repository.BestPictureNomineeRepository;
import com.backbase.movie.bestpicture.to.RateTo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toUnmodifiableMap;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CachedBestPictureService implements BestPictureService {

    private final BestPictureNomineeRepository bestPictureNomineeRepository;
    private final BestPictureMapper bestPictureMapper;
    /**
     * Cache(map) of movie titles(lowercase) to BestPictureNomineeTo objects
     */
    private Map<String, RateTo> bestPictureNomineeCache = Collections.emptyMap();

    @PostConstruct
    private void intiCache() {
        List<BestPictureNominee> bestPictures = bestPictureNomineeRepository.findAll();

        bestPictureNomineeCache = bestPictureMapper.mapToDtoList(bestPictures).stream()
                .collect(toUnmodifiableMap(to -> to.getTitle().trim().toLowerCase(), Function.identity()));

        log.info("Cache size is {}", bestPictureNomineeCache.size());
    }

    @Override
    public RateTo getBestPictureMovieByTitle(String title) throws MovieNotFoundException {

        RateTo bestPictureNomineeTo = bestPictureNomineeCache.get(title.trim().toLowerCase());
        if (bestPictureNomineeTo == null) {
            throw new MovieNotFoundException("Movie with the following title is not in the best picture nominees list: " + title);
        }

        return bestPictureNomineeTo;
    }

    /**
     * This method updates cache by the caller, it could be a github action or scheduled check that finds DB has been updated
     */
    public void updateCache() {
        log.info("A request for cache update received!");
        intiCache();
    }

}
