package com.blueharvest.demo.repository;

import com.blueharvest.demo.data.TransactionData;
import com.blueharvest.demo.model.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository{

    private TransactionData transactionData;

    public TransactionRepositoryImpl(){
        this.transactionData = TransactionData.getInstance();
    }

    @Override
    public Transaction findById(Long id) {
        return this.transactionData.findTransactionById(id);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return this.transactionData.addTransaction(transaction);
    }
}
