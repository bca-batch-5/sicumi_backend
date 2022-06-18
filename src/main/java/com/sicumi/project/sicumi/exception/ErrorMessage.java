package com.sicumi.project.sicumi.exception;

import java.util.Date;

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
  private Date timestamp;

  public ErrorMessage(Integer status, String message){
    this.status = status;
    this.message = message;
  }
  public ErrorMessage(int status, Date timestamp, String message){
    this.status = status;
    this.timestamp = timestamp;
    this.message = message;
  }
}