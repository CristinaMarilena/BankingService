package com.blueharvest.demo.service.entity;

import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.Transaction;

import java.math.BigDecimal;

public interface TransactionService {
    
    Transaction getTransactionById(Long id);
    Transaction saveTransaction(Transaction transaction);
    Transaction createSimpleTransaction(Account from, Account to, BigDecimal credit);
}
