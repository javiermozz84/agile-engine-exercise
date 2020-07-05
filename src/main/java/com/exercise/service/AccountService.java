package com.exercise.service;

import com.exercise.model.Account;
import com.exercise.model.Transaction;
import com.exercise.model.dto.TransactionRequestDTO;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    Account getAccountByUserId(Long idUser);

    List<Transaction> getAccountTransactions(Long accountId);

    Transaction saveTransaction(Long accountId, TransactionRequestDTO request);

    BigDecimal getAccountBalace(Long accountId);

}
