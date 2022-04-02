package com.backbase.movie.bestpicture.controller.error;

import com.backbase.movie.bestpicture.service.MovieNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({MovieNotFoundException.class})
    public ResponseEntity<ErrorTo> handleGameNotFoundException(MovieNotFoundException e, HttpServletResponse response) {
        log.debug(e.getMessage());

        return new ResponseEntity<>(new ErrorTo(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorTo> handleConstraintViolationException(ConstraintViolationException e, HttpServletResponse response) {
        log.debug(e.getMessage());

        String message = e.getConstraintViolations().stream()
                .map(this::constraintViolationToString)
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(new ErrorTo(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorTo> handleException(Exception e, HttpServletResponse response) {
        log.error("", e);

        return new ResponseEntity<>(new ErrorTo("An unexpected error occurred!"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private  String constraintViolationToString(ConstraintViolation cv) {
        return cv == null ? "null" : getLastPath(cv.getPropertyPath()) + ": " + cv.getMessage();
    }

    private String getLastPath(Path path) {
        String pathStr = String.valueOf(path);
        // Get last node to refer to property name only
        String[] nodes = pathStr.split("\\.");
        return nodes[nodes.length - 1];
    }

}
