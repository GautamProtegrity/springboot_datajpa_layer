package com.bl.data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(AuthorNotFoundException exception, WebRequest webRequest) {
        ResponseEntity<Object> entity = new ResponseEntity<>(
                GlobalExceptionResponse.builder()
                        .message(exception.getLocalizedMessage())
                        .dateTime(LocalDateTime.now())
                        .build(), HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(BookNotFoundException exception, WebRequest webRequest) {
        ResponseEntity<Object> entity = new ResponseEntity<>(
                GlobalExceptionResponse.builder()
                        .message(exception.getLocalizedMessage())
                        .dateTime(LocalDateTime.now())
                        .build(), HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(AuthorCreationException.class)
    public ResponseEntity<Object> handleExceptions(AuthorCreationException exception, WebRequest webRequest){
        ResponseEntity<Object> entity = new ResponseEntity<>(
                GlobalExceptionResponse.builder()
                        .message(exception.getLocalizedMessage())
                        .dateTime(LocalDateTime.now())
                        .build(), HttpStatus.EXPECTATION_FAILED);
        return entity;
    }

    @ExceptionHandler(AuthorProcessingException.class)
    public ResponseEntity<Object> handleExceptions(AuthorProcessingException exception, WebRequest webRequest) {
        ResponseEntity<Object> entity = new ResponseEntity<>(
                GlobalExceptionResponse.builder()
                        .message(exception.getLocalizedMessage())
                        .dateTime(LocalDateTime.now())
                        .build(), HttpStatus.EXPECTATION_FAILED);
        return entity;
    }

}
