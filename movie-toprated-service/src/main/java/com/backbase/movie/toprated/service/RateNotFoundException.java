package com.backbase.movie.toprated.service;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
public class RateNotFoundException extends Exception {

    public RateNotFoundException(String message) {
        super(message);
    }
}
