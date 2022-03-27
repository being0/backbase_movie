package com.backbase.movie.bestpicture.service;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
public class MovieNotFoundException extends Exception {

    public MovieNotFoundException(String message){
        super(message);
    }
}
