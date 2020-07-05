package com.exercise.service;

import com.exercise.model.Account;
import com.exercise.model.Transaction;
import com.exercise.model.User;
import com.exercise.model.enums.TransactionType;
import com.exercise.repository.AccountRepository;
import com.exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class H2DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    private boolean isReady = false;

    @Value("${initializer.user.name}")
    private String name;
    @Value("${initializer.user.lastname}")
    private String lastname;
    @Value("${initializer.user.userId}")
    private Long userId;
    @Value("${initializer.user.email}")
    private String email;


    @Autowired
    public H2DataInitializer(AccountService accountService, AccountRepository accountRepository, UserRepository userRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (isReady) { return; }
        createDefaultUser();
        isReady = true;
    }

    @Transactional
    protected void createDefaultUser() {
        Optional<User> propertiesUser = userRepository.findById(userId);

        if (!propertiesUser.isPresent()) {
            propertiesUser = Optional.of(new User(userId, name, lastname, email));
            User user = userRepository.save(propertiesUser.get());

            Account account = new Account();
            account.setUser(user);
            account.setAmount(new BigDecimal(0.0));
            account = accountRepository.save(account);

            Transaction transaction = new Transaction(new BigDecimal(25000), TransactionType.CREDIT, "Initial Transaction", account);

            account.setAmount( account.getAmount().add(transaction.getAmount()) );
            account.getTransactionList().add(transaction);

            accountRepository.save(account);
        }

    }


}
