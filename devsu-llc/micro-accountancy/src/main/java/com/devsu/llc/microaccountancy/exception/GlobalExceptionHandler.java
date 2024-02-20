package com.devsu.llc.microaccountancy.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundExceptionHandling(ResourceNotFoundException exception, WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                exception.getErrorCode(),
                request.getDescription(false),
                exception.getStackTrace());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<?> resourceBadRequestHandling(ResourceBadRequestException exception, WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST,
                exception.getErrorCode(),
                request.getDescription(false),
                exception.getStackTrace());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotEnoughAmountException.class)
    public ResponseEntity<?> resourceNotEnoughAmountHandling(ResourceNotEnoughAmountException exception, WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST,
                exception.getErrorCode(),
                request.getDescription(false),
                exception.getStackTrace());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
