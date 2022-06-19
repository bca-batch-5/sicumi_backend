package com.sicumi.project.sicumi.services;

import com.sicumi.project.sicumi.model.dto.ResponseData;
import com.sicumi.project.sicumi.model.dto.TransactionDto;

public interface TransactionService {
    ResponseData<Object> createTransaction(TransactionDto transactionDto);

    ResponseData<Object> getAll();

    ResponseData <Object> getTransactionDialy(Integer senderId);

    ResponseData <Object> getTransactionByUserId(Integer userId);

    ResponseData <Object> getContactByUserId(Integer userId);
}
