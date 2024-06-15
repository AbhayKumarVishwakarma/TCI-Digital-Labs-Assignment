package com.tci.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles NotFoundException and returns a custom error response
     * @param nfe The CandleException that was thrown
     * @param req The WebRequest
     * @return ResponseEntity containing the error details
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MyErrorDetails> candleExceptionHandler(NotFoundException nfe, WebRequest req){

        MyErrorDetails err= new MyErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(nfe.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles general Exception and returns a custom error response
     * @param se The Exception that was thrown
     * @param req The WebRequest
     * @return ResponseEntity containing the error details
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> globalExceptionHandler(Exception se, WebRequest req){

        MyErrorDetails err= new MyErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(se.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }
}
