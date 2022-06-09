package com.sicumi.project.sicumi.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewUserRequest {
  
  @NotEmpty(message = "Your Name is Required")
  private String name;

  @Email(message = "Email must be contain @ and .[com]")
  @NotEmpty(message = "Email is required")
  private String email;

  @NotEmpty(message = "Password is required")
  @Size(min = 8, max = 30, message = "Password length must between 8 and 20 characters")
  private String password;
}
