package com.blueharvest.demo.repository;

import com.blueharvest.demo.model.Transaction;

public interface TransactionRepository {

    Transaction findById(Long id);
    Transaction save(Transaction transaction);
}
