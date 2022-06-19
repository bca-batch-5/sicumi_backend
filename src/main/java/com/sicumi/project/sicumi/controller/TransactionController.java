package com.sicumi.project.sicumi.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.model.dto.TransactionDto;
import com.sicumi.project.sicumi.services.TransactionService;
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    ResponseData <Object> responseData;

    @PostMapping
    public ResponseEntity<?> createTransaction ( @RequestBody TransactionDto transactionDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(transactionService.createTransaction(transactionDto));
        } catch (Exception e) {
        responseData = new ResponseData<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), responseData);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
    }
    }

    @GetMapping 
    public ResponseEntity <?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(transactionService.getAll());
        } catch (Exception e) {
            responseData = new ResponseData<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    
    }

    @GetMapping("/{senderId}")
    public ResponseEntity<?> getTransactionDialy(@PathVariable Integer senderId){
       try {
           return ResponseEntity.status(HttpStatus.OK).body(transactionService.getTransactionDialy(senderId));
       } catch (Exception e) {
           responseData = new ResponseData<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
       }
    }

    @GetMapping ("/alltrans/{userId}")
    public ResponseEntity<?> getTransactionByUserId(@PathVariable Integer userId){
       try {
           return ResponseEntity.status(HttpStatus.OK.value()).body(transactionService.getTransactionByUserId(userId));
       } catch (Exception e) {
           responseData = new ResponseData<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
       }
    }

    @GetMapping("/contact/{senderId}")
    public ResponseEntity<?> getContactByUserId(@PathVariable Integer senderId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(transactionService.getContactByUserId(senderId));
        } catch (Exception e) {
            responseData = new ResponseData<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }
    
}