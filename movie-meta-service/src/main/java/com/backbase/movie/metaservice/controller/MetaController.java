package com.backbase.movie.metaservice.controller;

import com.backbase.movie.metaservice.to.MovieTo;
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
public class MetaController {

    @GetMapping
    public MovieTo getBestPictureMovieByTitle(@RequestParam("title") String title) {

        return null;
    }

}
