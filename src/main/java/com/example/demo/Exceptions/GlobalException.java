package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalException {

    @ExceptionHandler(FileUploadException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<StandarException> handleFileUploadException(FileUploadException e){
        HttpStatus status= HttpStatus.NOT_FOUND;
        StandarException error= new StandarException();
        error.setSms(e.getMessage());
        error.setStatus(status);

        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(EntitiesNotFoundException.class)
    public ResponseEntity<StandarException> handleEntitiesNotFoundException(EntitiesNotFoundException e){
        HttpStatus status= HttpStatus.NOT_FOUND;
        StandarException error= new StandarException();
        error.setSms(e.getMessage());
        error.setStatus(status);

        return ResponseEntity.status(status).body(error);
    }
}
