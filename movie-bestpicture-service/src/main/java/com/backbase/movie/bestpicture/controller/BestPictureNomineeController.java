package com.backbase.movie.bestpicture.controller;

import com.backbase.movie.bestpicture.service.BestPictureNomineeService;
import com.backbase.movie.bestpicture.service.MovieNotFoundException;
import com.backbase.movie.bestpicture.to.BestPictureNomineeTo;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/25/2022
 */
@RestController
@RequestMapping(value = "/movies/bestpictures")
@AllArgsConstructor
@Validated
public class BestPictureNomineeController {

    private final BestPictureNomineeService bestPictureNomineeService;

    @GetMapping
    public BestPictureNomineeTo getBestPictureMovieByTitle(
            @NotBlank
            @Size(min = 1, max = 50) @RequestParam("title") String title)
            throws MovieNotFoundException {

        return bestPictureNomineeService.getBestPictureMovieByTitle(title);
    }

}
