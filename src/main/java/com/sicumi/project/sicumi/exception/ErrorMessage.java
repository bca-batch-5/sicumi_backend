package com.sicumi.project.sicumi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage<T> {

  private Integer status;
  private String message;
  private T errors;

  public ErrorMessage(Integer status, String message){
    this.status = status;
    this.message = message;
  }
}
