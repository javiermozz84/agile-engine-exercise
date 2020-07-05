package com.exercise.service.impl;

import com.exercise.exception.NotFoundException;
import com.exercise.model.Account;
import com.exercise.model.Transaction;
import com.exercise.model.dto.TransactionRequestDTO;
import com.exercise.repository.AccountRepository;
import com.exercise.repository.TransactionRepository;
import com.exercise.service.AccountService;
import com.exercise.service.TransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;
    TransactionRepository transactionRepository;
    TransactionFactory transactionFactory;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, TransactionFactory transactionFactory) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionFactory = transactionFactory;
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
        Account account = getAccount(accountId);
        Transaction transaction = transactionFactory.create(request.getType()).getTransaction(request, account);
        transactionRepository.save( transaction );
        accountRepository.save( account );
        return transaction;
    }

    @Override
    public BigDecimal getAccountBalace(Long accountId) {
        return getAccount(accountId).getAmount();
    }

    private Account getAccount(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new NotFoundException("Account not found"));
    }
}
