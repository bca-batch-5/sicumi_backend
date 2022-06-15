package com.sicumi.project.sicumi.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicumi.project.sicumi.model.Transaction;
import com.sicumi.project.sicumi.model.User;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.model.dto.TransactionDto;
import com.sicumi.project.sicumi.repository.TransactionRepository;
import com.sicumi.project.sicumi.repository.UserRepository;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    ResponseData <Object> responseData;

    @PostMapping
    public ResponseEntity<?> createTransaction ( @RequestBody TransactionDto transactionDto){
        Optional<User> senderOps = userRepository.findById(transactionDto.getSenderId());
        Optional<User> receiverOps = userRepository.findById(transactionDto.getReceiverId());

        User sender = senderOps.get();
        User receiver = receiverOps.get();

        
        Transaction transaction = new Transaction(sender, receiver, new Date(), "Transfer", transactionDto.getTransAmount(), transactionDto.getNotes());

        transactionRepository.save(transaction);

        responseData= new ResponseData<Object>(HttpStatus.OK.value(), "succsess", transaction);
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    @GetMapping 
    public ResponseEntity <?> getAll(){
        List<Transaction> transactions = transactionRepository.findAll();
        
        responseData= new ResponseData<Object>(HttpStatus.OK.value(), "succsess", transactions);
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    
    }

    @GetMapping("/{senderId}")
    public ResponseEntity<?> getTransactionDialy(@PathVariable Integer senderId){
        List<Object> transactions = transactionRepository.getTransactionDaily(senderId);

        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "succsess", transactions);
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    
}