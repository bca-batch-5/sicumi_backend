package com.sicumi.project.sicumi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.model.dto.TopUpDto;
import com.sicumi.project.sicumi.services.TopUpService;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping ("/topup")
public class TopUpController {


    @Autowired 
    private TopUpService topUpService;

    ResponseData<Object> responseData;

    @PostMapping
    public ResponseEntity<?> createTopUp (@RequestBody TopUpDto topUpDto){
       try {
           return ResponseEntity.status(HttpStatus.OK).body(topUpService.createTopUp(topUpDto));
       } catch (Exception e) {
           responseData = new ResponseData<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
       }
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
       try {
           return ResponseEntity.status(HttpStatus.OK).body(topUpService.getAll());
       } catch (Exception e) {
        responseData = new ResponseData<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
       }
    }
}
