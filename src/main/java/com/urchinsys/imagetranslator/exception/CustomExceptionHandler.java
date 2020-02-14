package com.urchinsys.imagetranslator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {IllegalArgumentException.class, WordWasntFoundException.class})
  public ResponseEntity<Object> illegalArgument(RuntimeException ex, WebRequest request) {
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
}
