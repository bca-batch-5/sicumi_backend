package com.sicumi.project.sicumi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sicumi.project.sicumi.exception.custom.CustomNullException;


@ControllerAdvice
public class CustomExceptionHandler {
  ErrorMessage<Object> errorMessage;

  @ExceptionHandler(value = CustomNullException.class)
  public ResponseEntity<ErrorMessage<Object>> handlerNullException(Exception exception){
    errorMessage = new ErrorMessage<>(HttpStatus.NOT_FOUND.value(), exception.getMessage());

    return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
  }
}
