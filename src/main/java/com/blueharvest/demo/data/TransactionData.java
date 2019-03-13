package com.blueharvest.demo.data;

import com.blueharvest.demo.model.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TransactionData {

    private List<Transaction> transactions;

    private TransactionData() {
        addTransactionsToTheInitialList();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Transaction findTransactionById(Long id) {
        Optional<Transaction> transactionOptional = this.transactions.stream().filter(transaction -> transaction.getId().equals(id)).findFirst();
        return transactionOptional.orElse(null);
    }

    public List<Transaction> findTransactionByAccount(Long accountId) {
        return this.transactions.stream().filter(transaction -> transaction.getFromAccount().equals(accountId) || transaction.getToAccount().equals(accountId)).collect(Collectors.toList());
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
        transaction1.setAmount(BigDecimal.valueOf(200));
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
