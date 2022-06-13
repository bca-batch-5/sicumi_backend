package com.sicumi.project.sicumi.services;

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

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    // userList = new ArrayList<>(userRepository.findByEmail(email));
    // if(userList.isEmpty()){
    //   throw new UsernameNotFoundException("Email is not registered");
    // }
    // user = userList.get(0);
    User user = userRepository.findByEmail(email).get(0);
    if(user == null){
      throw new UsernameNotFoundException("Email is not registered 2");
    }
    
    String role = "USER";
    return UserDetailsImpl.build(user,role);
  }
  
}
