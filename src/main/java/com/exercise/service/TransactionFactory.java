package com.exercise.service;

import com.exercise.exception.TransactionException;
import com.exercise.model.enums.TransactionType;
import com.exercise.service.impl.CreditTransactionServiceImpl;
import com.exercise.service.impl.DebitTransactionServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TransactionFactory {
    public static TransactionTypeService create(TransactionType type) {
        switch (type) {
            case DEBIT:
                return new DebitTransactionServiceImpl();
            case CREDIT:
                return new CreditTransactionServiceImpl();
            default:
                throw new TransactionException("Not supported transaction type, should be CREDIT or DEBIT.");
        }
    }
}
