package com.backbase.movie.toprated.controller.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorTo> handleException(Exception e, HttpServletResponse response) {
        log.error("", e);

        return new ResponseEntity<>(new ErrorTo("An unexpected error occurred!"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
