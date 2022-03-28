package com.backbase.movie.toprated.controller.error;

import com.backbase.movie.toprated.service.RateNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RateNotFoundException.class})
    public ResponseEntity<ErrorTo> handleGameNotFoundException(RateNotFoundException e, HttpServletResponse response) {
        log.debug(e.getMessage());

        return new ResponseEntity<>(new ErrorTo(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorTo> handleConstraintViolationException(ConstraintViolationException e, HttpServletResponse response) {
        log.debug(e.getMessage());

        return new ResponseEntity<>(new ErrorTo(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
