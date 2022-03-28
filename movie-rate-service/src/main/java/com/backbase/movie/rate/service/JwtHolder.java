package com.backbase.movie.rate.service;

import org.springframework.security.oauth2.jwt.Jwt;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/28/2022
 */
public interface JwtHolder {

    /**
     * Provide JWT normally from context
     *
     * @return jwt token
     */
    Jwt getJwt();
}
