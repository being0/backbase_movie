package com.backbase.movie.toprated.controller;

import com.backbase.movie.toprated.service.RateNotFoundException;
import com.backbase.movie.toprated.service.RateService;
import com.backbase.movie.toprated.to.RateTo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/25/2022
 */
@RestController
@RequestMapping(value = "/movies/{id}/rate")
@AllArgsConstructor
public class RateController {

    private final RateService rateService;

    @PostMapping
    public RateTo postRate(@PathVariable("id") String movieId, @RequestBody RateTo rateTo) {

        return rateService.postRate(movieId, rateTo);
    }

    @GetMapping
    public RateTo getRate(@PathVariable("id") String movieId) throws RateNotFoundException {

        return rateService.getRate(movieId);
    }

    @DeleteMapping
    public void deleteRate(@PathVariable("id") String movieId) {

        rateService.deleteRate(movieId);
    }

}
