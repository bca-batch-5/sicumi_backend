package com.sicumi.project.sicumi.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sicumi.project.sicumi.exception.custom.CustomNullException;
import com.sicumi.project.sicumi.model.User;
import com.sicumi.project.sicumi.repository.UserRepository;

@Service
public class UserValidator {
  
  User user;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;
  
  public void findEmailValidation(List<User> userList) throws CustomNullException{
    if(userList.isEmpty()){
      throw new CustomNullException("Email is not registered");
    }
  }

  public String checkPasswordValidation(String loginPass, String dbPass) throws CustomNullException{
    // if(!loginPass.equals(dbPass)){
    //   throw new CustomNullException("Password is not correct");
    // }
    if(!passwordEncoder.matches(loginPass, dbPass)){
      throw new CustomNullException("Pasword is not correct");
    }
    return dbPass;
  }

  public void availableEmailValidation(List<User> userList) throws CustomNullException{
    if(!userList.isEmpty()){
      throw new CustomNullException("Email is already use");
    }
  }
}
