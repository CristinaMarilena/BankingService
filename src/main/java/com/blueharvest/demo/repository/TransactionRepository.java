package com.blueharvest.demo.repository;

import com.blueharvest.demo.model.Transaction;

import java.util.List;

public interface TransactionRepository {

    Transaction findById(Long id);
    Transaction save(Transaction transaction);
    List<Transaction> findByAccount(Long accountId);
}
