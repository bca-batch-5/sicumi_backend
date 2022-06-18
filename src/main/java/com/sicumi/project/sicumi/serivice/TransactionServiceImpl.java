package com.sicumi.project.sicumi.serivice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sicumi.project.sicumi.model.Transaction;
import com.sicumi.project.sicumi.model.User;
import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.model.dto.TransactionDto;
import com.sicumi.project.sicumi.repository.TransactionRepository;
import com.sicumi.project.sicumi.repository.UserRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{

    ResponseData<Object> responseData;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseData<Object> createTransaction(TransactionDto transactionDto) {
        Optional<User> senderOps = userRepository.findById(transactionDto.getSenderId());
        Optional<User> receiverOps = userRepository.findById(transactionDto.getReceiverId());

        User sender = senderOps.get();
        User receiver = receiverOps.get();

        
        Transaction transaction = new Transaction(sender, receiver, new Date(), "Transfer", transactionDto.getTransAmount(), transactionDto.getNotes());

        transactionRepository.save(transaction);

        responseData= new ResponseData<Object>(HttpStatus.OK.value(), "succsess", transaction);
        return responseData;
    }

    @Override
    public ResponseData<Object> getAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        
        responseData= new ResponseData<Object>(HttpStatus.OK.value(), "succsess", transactions);
        return responseData;
    }

    @Override
    public ResponseData<Object> getTransactionDialy(Integer senderId) {
        List<Object> transactions = transactionRepository.getTransactionDaily(senderId);

        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "succsess", transactions);
        return responseData;
    }

    @Override
    public ResponseData<Object> getTransactionByUserId(Integer userId) {
        List<Transaction> sendList= transactionRepository.getBySenderIdId(userId);
        List<Transaction> receiveList = transactionRepository.getByReceiverIdId(userId);

        List<Object> transList = new ArrayList<>(){{addAll(sendList); addAll(receiveList);}};

        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "succsess", transList);

        return responseData;
    }

    @Override
    public ResponseData<Object> getContactByUserId(Integer senderId) {
        List<Object> contact = transactionRepository.getContact(senderId);

        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "succsess", contact);

        return responseData;
    }
    
}
