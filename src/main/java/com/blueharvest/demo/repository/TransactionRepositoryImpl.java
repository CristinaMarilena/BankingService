package com.blueharvest.demo.repository;

import com.blueharvest.demo.data.TransactionData;
import com.blueharvest.demo.model.Transaction;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository{

    private TransactionData transactionData;

    @Inject
    public TransactionRepositoryImpl(TransactionData transactionData){
        this.transactionData = transactionData;
    }

    @Override
    public Transaction findById(Long id) {
        return this.transactionData.findTransactionById(id);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return this.transactionData.addTransaction(transaction);
    }

    @Override
    public List<Transaction> findByAccount(Long accountId) {
        return this.transactionData.findTransactionByAccount(accountId);
    }
}
