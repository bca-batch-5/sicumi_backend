package com.sicumi.project.sicumi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicumi.project.sicumi.model.DetailUser;
import com.sicumi.project.sicumi.model.dto.DetailUserDto;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.repository.DetailUserRepository;
import com.sicumi.project.sicumi.serivice.DetailUserService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/DetailUser")
public class DetailUserController {
    @Autowired
    private DetailUserRepository detailUserRepository;

    @Autowired
    private DetailUserService detailUserService;

    ResponseData <Object> responseData;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable Integer userId){
        try {

            return ResponseEntity.status(HttpStatus.OK).body(detailUserService.getByUserId(userId));
        } catch (Exception e) {
            responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null);
            return ResponseEntity.ok(responseData);
        }
        
        
    }
     
    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateBalance(@PathVariable Integer userId, @RequestBody DetailUserDto detailUserDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(detailUserService.updateBalance(userId, detailUserDto));
        } catch (Exception e) {
            responseData = new ResponseData<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    

}
