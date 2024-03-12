package com.ecommerce.prices.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<Object> noSuchElementException(NoSuchElementException ex, WebRequest request) {
    return new ResponseEntity<>(ExceptionDTO.builder().errorCode("000").message("Resource not found.").build(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Object> httpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
    return new ResponseEntity<>(ExceptionDTO.builder().errorCode("001").message("Request error. Review the parameters provided.").details(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> exception(Exception ex, WebRequest request) {
    return new ResponseEntity<>(ExceptionDTO.builder().errorCode("000").message("Unhandled error.").details(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
    var details = new ArrayList<>();
    ex.getBindingResult().getFieldErrors().forEach(field -> details.add(field.getField() + ": " + field.getDefaultMessage()));
    return new ResponseEntity<>(ExceptionDTO.builder().errorCode("001").message("Request error. Review the parameters provided.").details(details).build(), HttpStatus.BAD_REQUEST);
  }

}
