package com.ecommerce.prices.infrastructure.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


class GlobalExceptionHandlerTest {
  private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

  @Test
  public void testNoSuchElementException() {
    NoSuchElementException ex = new NoSuchElementException("Element not found");
    ResponseEntity<Object> responseEntity = handler.noSuchElementException(ex, mock(WebRequest.class));
    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
  }

  @Test
  public void testHttpMessageNotReadableException() {
    HttpMessageNotReadableException ex = new HttpMessageNotReadableException("Message not readable");
    ResponseEntity<Object> responseEntity = handler.httpMessageNotReadableException(ex, mock(WebRequest.class));
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }

  @Test
  public void testPriceException() {
    Exception ex = new Exception("Unhandled error");
    ResponseEntity<Object> responseEntity = handler.exception(ex, mock(WebRequest.class));
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
  }

  @Test
  public void testMethodArgumentNotValidException() {
    List<FieldError> fieldErrors = new ArrayList<>();
    fieldErrors.add(new FieldError("productId", "productId", "must not be null"));

    BindingResult bindingResult = mock(BindingResult.class);
    for (FieldError fieldError : fieldErrors) {
      bindingResult.addError(fieldError);
    }

    MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);
    ResponseEntity<Object> responseEntity = handler.methodArgumentNotValidException(ex, mock(WebRequest.class));
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }
}