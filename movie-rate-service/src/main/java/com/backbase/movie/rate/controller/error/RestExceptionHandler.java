package com.backbase.movie.rate.controller.error;

import com.backbase.movie.bestpicture.service.MovieNotFoundException;
import com.backbase.movie.rate.controller.error.ErrorTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({MovieNotFoundException.class})
    public ResponseEntity<ErrorTo> handleGameNotFoundException(MovieNotFoundException e, HttpServletResponse response) throws IOException {
        log.debug(e.getMessage());

        return new ResponseEntity<>(new ErrorTo(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorTo> handleConstraintViolationException(ConstraintViolationException e, HttpServletResponse response) throws IOException {
        log.debug(e.getMessage());

        return new ResponseEntity<>(new ErrorTo(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
