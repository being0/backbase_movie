package com.backbase.movie.rate.controller;

import com.backbase.movie.bestpicture.service.BestPictureService;
import com.backbase.movie.bestpicture.service.MovieNotFoundException;
import com.backbase.movie.bestpicture.to.BestPictureNomineeTo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/25/2022
 */
@RestController
@RequestMapping(value = "/movies/{:id}/rate")
@AllArgsConstructor
public class RateController {

    private final BestPictureService bestPictureService;

    @PostMapping
    public BestPictureNomineeTo getBestPictureMovieByTitle(@RequestParam("title") String title) throws MovieNotFoundException {

        return bestPictureService.getBestPictureMovieByTitle(title);
    }

}
