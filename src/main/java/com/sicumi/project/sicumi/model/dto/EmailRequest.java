package com.sicumi.project.sicumi.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {

  @Email(message = "Email must be contain @ and .[com]")
  @NotEmpty(message = "Email is required")
  private String email;

  }
