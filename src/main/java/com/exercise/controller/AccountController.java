package com.exercise.controller;

import com.exercise.model.dto.TransactionRequestDTO;
import com.exercise.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/accounting/api")
public class AccountController {

    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("user/{userId}/account")
    public ResponseEntity<?> getUserAccount(@PathVariable Long userId) {
        return ResponseEntity.ok(this.accountService.getAccountByUserId(userId));
    }

    @GetMapping("account/{accountId}/transactions")
    public ResponseEntity<?> getAccountTransactions(@PathVariable Long accountId) {
        return ResponseEntity.ok(this.accountService.getAccountTransactions(accountId));
    }

    @PostMapping("account/{accountId}/transactions")
    public ResponseEntity<?> saveAccountTransaction(@RequestBody TransactionRequestDTO requestTransaction, @PathVariable Long accountId) {
        return ResponseEntity.ok(this.accountService.saveTransaction(accountId, requestTransaction));
    }

}
