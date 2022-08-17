package com.example.StudentApi.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;


@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<?> handleMethodArgumentNotValid (MethodArgumentNotValidException ex) {

    StringBuilder errors = new StringBuilder();

    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
      errors.append(String.format(Objects.requireNonNull(fieldError.getDefaultMessage()), fieldError.getField()))
          .append("\n");
    }
    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.append(error.getDefaultMessage());
    }
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

}
