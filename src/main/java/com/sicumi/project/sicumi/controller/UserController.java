package com.sicumi.project.sicumi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicumi.project.sicumi.exception.custom.CustomNullException;
import com.sicumi.project.sicumi.model.dto.ChangePasswordRequest;
import com.sicumi.project.sicumi.model.dto.EmailRequest;
import com.sicumi.project.sicumi.model.dto.LoginRequest;
import com.sicumi.project.sicumi.model.dto.NewUserRequest;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.services.UserServices;

@RestController
@RequestMapping("/user")
public class UserController {
  
  private ResponseData<Object> responseData;

  @Autowired
  private UserServices userServices;

  @GetMapping("/signin")
  public ResponseEntity<?> getUser(@RequestBody @Valid LoginRequest loginData) throws CustomNullException{
    responseData =  userServices.getUserLogin(loginData);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> addUser(@RequestBody @Valid NewUserRequest signupData) throws CustomNullException{
    responseData =  userServices.createNewUser(signupData);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @PutMapping("/resetpassword")
  public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordRequest resetPassword ) throws CustomNullException{
    responseData = userServices.changePassword(resetPassword);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }
  
  @GetMapping("/find")
  public ResponseEntity<?> getEmail(@RequestBody @Valid EmailRequest dataEmail) throws CustomNullException{
    responseData =  userServices.findEmail(dataEmail);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }
}
