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
        return this.transactions;
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
        transaction1.setFromAccount(1L);
        transaction1.setToAccount(43242L);
        transaction1.setAmount(BigDecimal.valueOf(200));
        this.transactions.add(transaction1);

    }
}
