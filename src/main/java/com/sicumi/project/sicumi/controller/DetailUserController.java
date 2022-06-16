package com.sicumi.project.sicumi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicumi.project.sicumi.model.DetailUser;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.repository.DetailUserRepository;

@RestController
@RequestMapping("/DetailUser")
public class DetailUserController {
    @Autowired
    private DetailUserRepository detailUserRepository;

    ResponseData <Object> responseData;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable Integer userId){
        DetailUser detailUser = detailUserRepository.getByUserIdId(userId);

        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "succsess", detailUser);
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    

}
