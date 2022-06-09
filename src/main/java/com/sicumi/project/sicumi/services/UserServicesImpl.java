package com.sicumi.project.sicumi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sicumi.project.sicumi.exception.custom.CustomNullException;
import com.sicumi.project.sicumi.model.User;
import com.sicumi.project.sicumi.model.dto.LoginRequest;
import com.sicumi.project.sicumi.model.dto.NewUserRequest;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.repository.UserRepository;
import com.sicumi.project.sicumi.validator.UserValidator;

@Service
@Transactional
public class UserServicesImpl implements UserServices {

  ResponseData<Object> responseData;

  User user;
  
  List<User> userList;
  
  @Autowired
  UserValidator userValidator;

  @Autowired
  UserRepository userRepository;
  

  @Override
  public ResponseData<Object> getUserLogin(LoginRequest loginData) throws CustomNullException {
  userList = new ArrayList<>(userRepository.findByEmail(loginData.getEmail()));
  userValidator.findEmailValidation(userList);
  
  user = userList.get(0);
  userValidator.checkPasswordValidation(loginData.getPassword(), user.getPassword());
  
  responseData = new ResponseData<Object>(HttpStatus.FOUND.value(), "Login Success", user);
  return responseData;
}

@Override
public ResponseData<Object> createNewUser(NewUserRequest signUpData) throws CustomNullException {
  userList = new ArrayList<>(userRepository.findByEmail(signUpData.getEmail()));
  userValidator.availableEmailValidation(userList);

  user = new User(signUpData.getName(), signUpData.getEmail(), signUpData.getPassword());
  userRepository.save(user);

  responseData = new ResponseData<Object>(HttpStatus.OK.value(), "Signup Success", user);
  return responseData;
  }

  
}
