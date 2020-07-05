package com.exercise.service;

import com.exercise.model.Account;
import com.exercise.model.Transaction;
import com.exercise.model.dto.TransactionRequestDTO;

public interface TransactionTypeService {

    Transaction getTransaction(TransactionRequestDTO request, Account account);
    void validate(TransactionRequestDTO request, Account account);
}
