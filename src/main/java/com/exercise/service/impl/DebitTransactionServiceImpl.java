package com.exercise.service.impl;

import com.exercise.exception.BalanceException;
import com.exercise.model.Account;
import com.exercise.model.Transaction;
import com.exercise.model.dto.TransactionRequestDTO;
import com.exercise.service.TransactionTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class DebitTransactionServiceImpl implements TransactionTypeService {

    @Override
    public Transaction getTransaction(TransactionRequestDTO request, Account account) {
        this.validate(request, account);
        Transaction transaction = new Transaction(request.getAmount(), request.getType(), "Debit request transaction", account);
        account.setAmount(account.getAmount().add(request.getAmount()));
        account.getTransactionList().add(transaction);
        return transaction;
    }

    @Override
    public void validate(TransactionRequestDTO request, Account account) {
        if (request.getAmount().compareTo(BigDecimal.ZERO)  == 1) {
            throw new BalanceException("Debit amount must be a negative number");
        }
        if (account.getAmount().add(request.getAmount()).compareTo(BigDecimal.ZERO) == -1) {
            throw new BalanceException("Balance can't be less than zero");
        }
    }


}
