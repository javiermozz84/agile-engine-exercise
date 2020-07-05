package com.exercise.service.impl;

import com.exercise.exception.NotFoundException;
import com.exercise.model.Account;
import com.exercise.model.Transaction;
import com.exercise.model.dto.TransactionRequestDTO;
import com.exercise.repository.AccountRepository;
import com.exercise.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccountByUserId(Long idUser) {
        return accountRepository.findByUserId(idUser).orElseThrow(() -> new NotFoundException("Account not found"));
    }

    @Override
    public List<Transaction> getAccountTransactions(Long accountId) {
        return getAccount(accountId).getTransactionList();
    }

    @Override
    public Transaction saveTransaction(Long accountId, TransactionRequestDTO request) {
        //TODO implement save
        return null;
    }

    private Account getAccount(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new NotFoundException("Account not found"));
    }
}
