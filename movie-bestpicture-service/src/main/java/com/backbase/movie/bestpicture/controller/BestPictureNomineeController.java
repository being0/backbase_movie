package com.backbase.movie.bestpicture.controller;

import com.backbase.movie.bestpicture.service.BestPictureNomineeService;
import com.backbase.movie.bestpicture.service.MovieNotFoundException;
import com.backbase.movie.bestpicture.to.BestPictureNomineeTo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * This service check whether a movie is on the "Best Picture Nominees list".
 * If movie can not be found error 404 will be raised else the movie with its winning status will be returned.
 *
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/25/2022
 */
@RestController
@RequestMapping(value = "/movies/bestpictures")
@AllArgsConstructor
@Validated
@Slf4j
public class BestPictureNomineeController {

    private final BestPictureNomineeService bestPictureNomineeService;

    @GetMapping
    public BestPictureNomineeTo getBestPictureMovieByTitle(
            @NotBlank
            @Size(min = 1, max = 50) @RequestParam("title") String title)
            throws MovieNotFoundException {

        if (log.isDebugEnabled()) log.debug("GET /movies/bestpictures by title {} called.", title);

        return bestPictureNomineeService.getBestPictureMovieByTitle(title);
    }

}
