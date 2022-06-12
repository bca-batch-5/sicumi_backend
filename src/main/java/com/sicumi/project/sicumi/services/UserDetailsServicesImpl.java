package com.sicumi.project.sicumi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sicumi.project.sicumi.model.User;
import com.sicumi.project.sicumi.repository.UserRepository;

@Service
@Transactional
public class UserDetailsServicesImpl implements UserDetailsService {

  List<User> userList;

  User user;

  @Autowired
  UserRepository userRepository;

  UserDetailsImpl userDetailsImpl;
  
  
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    userList = new ArrayList<>(userRepository.findByEmail(email));
    if(userList.isEmpty()){
      throw new UsernameNotFoundException("Email is not registered");
    }
    user = userList.get(0);

    String role = "USER";
    
    return userDetailsImpl.build(user,role);
  }
  
}
