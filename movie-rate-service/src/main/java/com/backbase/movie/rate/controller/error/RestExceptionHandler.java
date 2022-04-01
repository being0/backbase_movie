package com.backbase.movie.rate.controller.error;

import com.backbase.movie.rate.service.RateNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.Collection;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RateNotFoundException.class})
    public ResponseEntity<ErrorTo> handleRateNotFoundException(RateNotFoundException e, HttpServletResponse response) {
        log.debug(e.getMessage());

        return new ResponseEntity<>(new ErrorTo(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorTo> handleConstraintViolationException(ConstraintViolationException e, HttpServletResponse response) {
        log.debug(e.getMessage());

        String message = e.getConstraintViolations() == null ? "" : e.getConstraintViolations().stream()
                .map(cv -> cv == null ? "null" : getLastPath(cv.getPropertyPath()) + ": " + cv.getMessage())
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(new ErrorTo(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorTo> handleExcp(Exception e, HttpServletResponse response) {
        log.debug(e.getMessage());

        return new ResponseEntity<>(new ErrorTo(e.getMessage()), HttpStatus.BAD_REQUEST);
    }


    private String getLastPath(Path path) {
        String pathStr = String.valueOf(path);
        // Get last node to refer to property name only
        String[] nodes = pathStr.split("\\.");
        return nodes[nodes.length - 1];
    }

}
