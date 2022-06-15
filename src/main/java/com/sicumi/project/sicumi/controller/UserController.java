package com.sicumi.project.sicumi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicumi.project.sicumi.model.User;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    ResponseData <Object> responseData;

    @GetMapping 
    public ResponseEntity<?> getAll(){
        List<User> users = userRepository.findAll();

        responseData = new ResponseData<Object>(HttpStatus.OK.value(),"Succsess", users);
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }
}
