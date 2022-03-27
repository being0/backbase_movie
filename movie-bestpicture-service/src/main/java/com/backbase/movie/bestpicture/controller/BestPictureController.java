package com.backbase.movie.bestpicture.controller;

import com.backbase.movie.bestpicture.service.BestPictureService;
import com.backbase.movie.bestpicture.service.MovieNotFoundException;
import com.backbase.movie.bestpicture.to.RateTo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/25/2022
 */
@RestController
@RequestMapping(value = "/movies/bestpicture")
@AllArgsConstructor
public class BestPictureController {

    private final BestPictureService bestPictureService;

    @GetMapping
    public RateTo getBestPictureMovieByTitle(@RequestParam("title") String title) throws MovieNotFoundException {

        return bestPictureService.getBestPictureMovieByTitle(title);
    }

}
