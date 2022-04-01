package com.backbase.movie.rate.controller;

import com.backbase.movie.rate.service.RateNotFoundException;
import com.backbase.movie.rate.service.RateService;
import com.backbase.movie.rate.to.RateTo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/25/2022
 */
@RestController
@RequestMapping(value = "/movies/{id}/users/rate")
@RequiredArgsConstructor
@Validated
public class RateController {

    private final RateService rateService;

    @PostMapping
    public RateTo postRate(@Size(min = 1, max = 20) @PathVariable("id") String movieId, @RequestBody RateTo rateTo) {

        return rateService.postRate(movieId, rateTo);
    }

    @GetMapping
    public RateTo getRate(@Size(min = 1, max = 20) @PathVariable("id") String movieId) throws RateNotFoundException {

        return rateService.getRate(movieId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRate(@Size(min = 1, max = 20) @PathVariable("id") String movieId) {

        rateService.deleteRate(movieId);
    }

}
