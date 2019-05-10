package com.blueharvest.demo.service.entity;

import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    
    Transaction getTransactionById(Long id);
    Transaction saveTransaction(Transaction transaction);
    Transaction createSimpleTransaction(Account from, Account to, BigDecimal credit);
    List<Transaction> findByAccount(Account account);
}
