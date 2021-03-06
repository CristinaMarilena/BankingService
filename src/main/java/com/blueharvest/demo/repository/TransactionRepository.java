package com.blueharvest.demo.repository;

import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findByFromAccountOrToAccount(Account fromAccount, Account toAccount);
}
