package com.exercise;

import com.exercise.exception.BalanceException;
import com.exercise.model.Account;
import com.exercise.model.Transaction;
import com.exercise.model.User;
import com.exercise.model.dto.TransactionRequestDTO;
import com.exercise.model.enums.TransactionType;
import com.exercise.repository.AccountRepository;
import com.exercise.repository.UserRepository;
import com.exercise.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ExerciseApplicationTests {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;


    Optional<User> user;
    List<Transaction> transactions;
    Account account;

    @Value("${initializer.user.name}")
    private String name;
    @Value("${initializer.user.userId}")
    private Long userId;

    @Before
    public void setUp() {
        user = userRepository.findById(1L);
        transactions = accountService.getAccountTransactions(1L);
        account = accountRepository.findById(1L).get();
    }

    @Test
    public void contextLoads() {
        assertEquals(user.get().getName(), this.name);
        assertEquals(user.get().getId(), this.userId);
        assertThat(transactions, hasSize(1));

        List<Transaction> accountTransactions = accountService.getAccountByUserId(user.get().getId()).getTransactionList();
        assertThat(accountTransactions, hasSize(1));

        BigDecimal balance = new BigDecimal(accountService.getAccountBalace(account.getId()).toString());
        assertEquals(balance, new BigDecimal("25000.00"));
    }

    @Test
    public void debitSuccess() {
        TransactionRequestDTO transactionRequest = new TransactionRequestDTO(new BigDecimal(-5000.00), TransactionType.DEBIT);
        accountService.saveTransaction(account.getId(), transactionRequest);
        List<Transaction> accountTransactions = accountService.getAccountTransactions(account.getId());
        assertThat(accountTransactions, hasSize(2));

        BigDecimal balance = accountService.getAccountBalace(account.getId());
        assertEquals(balance, new BigDecimal("20000.00"));
    }

    @Test(expected = BalanceException.class)
    public void debitBalanceFailure() {
        TransactionRequestDTO transactionRequest = new TransactionRequestDTO(new BigDecimal(-30000.00), TransactionType.DEBIT);
        accountService.saveTransaction(account.getId(), transactionRequest);
    }

    @Test
    public void creditSuccess() {
        TransactionRequestDTO transactionRequest = new TransactionRequestDTO(new BigDecimal(5000.00), TransactionType.CREDIT);
        accountService.saveTransaction(account.getId(), transactionRequest);
        List<Transaction> accountTransactions = accountService.getAccountTransactions(account.getId());
        assertThat(accountTransactions, hasSize(2));

        BigDecimal balance = accountService.getAccountBalace(account.getId());
        assertEquals(balance, new BigDecimal("30000.00"));
    }
}
