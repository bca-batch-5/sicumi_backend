package com.sicumi.project.sicumi.services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sicumi.project.sicumi.config.JwtUtil;
import com.sicumi.project.sicumi.exception.custom.CustomNullException;
import com.sicumi.project.sicumi.model.User;
import com.sicumi.project.sicumi.model.dto.ChangePasswordRequest;
import com.sicumi.project.sicumi.model.dto.EmailRequest;
import com.sicumi.project.sicumi.model.dto.LoginRequest;
import com.sicumi.project.sicumi.model.dto.NewUserRequest;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.repository.UserRepository;
import com.sicumi.project.sicumi.validator.UserValidator;

@Service
@Transactional
public class UserServicesImpl implements UserServices {

  private ResponseData<Object> responseData;

  private User user;
  
  private List<User> userList;

  private Map<String, Object> data;
  
  @Autowired
  private UserValidator userValidator;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthenticationManager authenticationManager;
  
  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private EmailSenderServices mailService;

  @Override
  public ResponseData<Object> getUserLogin(LoginRequest loginData) throws CustomNullException {
  userList = new ArrayList<>(userRepository.findByEmail(loginData.getEmail()));
  userValidator.findEmailValidation(userList);
  
  user = userList.get(0);
  userValidator.checkPasswordValidation(loginData.getPassword(), user.getPassword());
  
  
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginData.getEmail(), loginData.getPassword());
    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = jwtUtil.generateJwToken(authentication);
    UserDetails userDetails = (UserDetailsImpl) authentication.getPrincipal();


    data = new HashMap<>();
    data.put("token", token);
    data.put("email", userDetails.getUsername());
  
  responseData = new ResponseData<Object>(HttpStatus.FOUND.value(), "Login Success", data);
  return responseData;
}

  @Override
  public ResponseData<Object> createNewUser(NewUserRequest signUpData) throws CustomNullException {
  userList = new ArrayList<>(userRepository.findByEmail(signUpData.getEmail()));
  userValidator.availableEmailValidation(userList);

  user = new User(signUpData.getName(), signUpData.getEmail(), signUpData.getPassword(), signUpData.getPin());
  user.setPassword(passwordEncoder.encode(signUpData.getPassword()));
  userRepository.save(user);

  responseData = new ResponseData<Object>(HttpStatus.OK.value(), "Signup Success", user);
  return responseData;
}

@Override
public ResponseData<Object> changePassword(ChangePasswordRequest resetPassword) throws CustomNullException {
  userList = new ArrayList<>(userRepository.findByEmail(resetPassword.getEmail()));
  user = userList.get(0);
  user.setPassword(passwordEncoder.encode(resetPassword.getPassword()));
  userRepository.save(user);
  
  responseData = new ResponseData<Object>(HttpStatus.ACCEPTED.value(), "Reset Password Success", user);
  return responseData;
}

@Override
public ResponseData<Object> findEmail(EmailRequest email) throws CustomNullException {
  userList = new ArrayList<>(userRepository.findByEmail(email.getEmail()));
  userValidator.findEmailValidation(userList);
  String encodedString = Base64.getEncoder().encodeToString(email.getEmail().getBytes());
  String localLink = "Click this link : http://localhost:3000/Reset/Confirm/"+encodedString+" to continue and reset your password";
  mailService.sendEmail("andre.ayadi46@gmail.com", "Password Reset Request", localLink);
  
  responseData = new ResponseData<Object>(HttpStatus.FOUND.value(), "Email Verification Sended", email);
  return responseData;
}

@Override
public ResponseData<Object> checkEmail(EmailRequest email) throws CustomNullException {
  userList = new ArrayList<>(userRepository.findByEmail(email.getEmail()));
  userValidator.availableEmailValidation(userList);

  responseData = new ResponseData<Object>(HttpStatus.OK.value(), "Email Available", email);
  return responseData;
}

}
