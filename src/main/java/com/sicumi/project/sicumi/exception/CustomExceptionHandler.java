package com.sicumi.project.sicumi.exception;

import java.util.Date;

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

//   @ExceptionHandler(value = CustomUnauthorizedException.class)
//   public ResponseEntity<ErrorMessage<Object>> handlerUnauthorizedException(Exception exception){
//     errorMessage = new ErrorMessage<>(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());

//     return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
//   }

  @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorMessage<Object>> globaException(Exception exception) {
        errorMessage = new ErrorMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), exception.getMessage());
        return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage<Object>> globalRuntimeExcEntity(RuntimeException exception) {
        errorMessage = new ErrorMessage<>(HttpStatus.BAD_REQUEST.value(), new Date(), exception.getMessage());
        return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
    }

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<ErrorMessage<Object>> dataNotFound(DataNotFoundException exception) {
        errorMessage = new ErrorMessage<>(HttpStatus.NOT_FOUND.value(), new Date(), exception.getMessage());
        return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
    }

    @ExceptionHandler(value = FileStorageException.class)
    public ResponseEntity<?> handleFileStorageException(FileStorageException exception) {
        errorMessage = new ErrorMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), exception.getMessage());
        return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
    }

    // @ExceptionHandler(value = CustomNullException.class)
    // public ResponseEntity<?> handleNullException(CustomNullException exception) {
    //     errorMessage = new ErrorMessage<>(HttpStatus.NOT_FOUND.value(), new Date(), exception.getMessage());
    //     return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
    // }

    // @ExceptionHandler(value = CustomUnauthorizedException.class)
    // public ResponseEntity<?> handleUnauthorizedException(CustomUnauthorizedException exception) {
    //     errorMessage = new ErrorMessage<>(HttpStatus.UNAUTHORIZED.value(), new Date(), exception.getMessage());
    //     return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
    // }
}