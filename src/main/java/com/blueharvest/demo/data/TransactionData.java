package com.blueharvest.demo.data;

import com.blueharvest.demo.model.Transaction;
import com.blueharvest.demo.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionData {

    private volatile static TransactionData instance;
    private List<Transaction> transactions;

    private TransactionData() {
        addTransactionsToTheInitialList();
    }

    public static TransactionData getInstance() {
        if (instance == null) {
            synchronized (TransactionData.class) {
                if (instance == null) {
                    instance = new TransactionData();
                }
            }
        }

        return instance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Transaction findTransactionById(Long id) {
        return this.transactions.stream().filter(user -> user.getId() == id).findFirst().get();
    }

    public Transaction addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        return transaction;
    }

    private void addTransactionsToTheInitialList() {
        this.transactions = new ArrayList<>();

        Transaction transaction1 = new Transaction();
        transaction1.setId(1L);
        transaction1.setFromAccount(3L);
        transaction1.setToAccount(4L);
        transaction1.setAmmount(BigDecimal.valueOf(200));
        this.transactions.add(transaction1);

/*        Transaction transaction2 = new Transaction();
        transaction2.setId(2L);
        this.transactions.add(transaction2);

        Transaction transaction3 = new Transaction();
        transaction3.setId(3L);
        this.transactions.add(transaction3);

        Transaction transaction4 = new Transaction();
        transaction4.setId(4L);
        this.transactions.add(transaction4);

        Transaction transaction5 = new Transaction();
        transaction5.setId(5L);
        this.transactions.add(transaction5);

        Transaction transaction6 = new Transaction();
        transaction6.setId(6L);
        this.transactions.add(transaction6);

        Transaction transaction7 = new Transaction();
        transaction7.setId(7L);
        this.transactions.add(transaction7);*/
    }

}
