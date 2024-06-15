package com.tci.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MyErrorDetails> notFoundExceptionHandler(NotFoundException nfe, WebRequest req){

        MyErrorDetails err= new MyErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(nfe.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> globalExceptionHandler(Exception se, WebRequest req){

        MyErrorDetails err= new MyErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(se.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }
}
