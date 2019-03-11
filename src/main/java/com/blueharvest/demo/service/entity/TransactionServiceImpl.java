package com.blueharvest.demo.service.entity;

import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.Transaction;
import com.blueharvest.demo.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;

import static com.blueharvest.demo.utils.RandomGenerator.randomId;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    @Inject
    TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction createSimpleTransaction(Account fromAccount, Account toAccount, BigDecimal credit) {
        Transaction transaction = new Transaction();
        transaction.setId(randomId());
        transaction.setAmmount(credit);
        transaction.setFromAccount(fromAccount.getId());
        transaction.setToAccount(toAccount.getId());

        return transaction;
    }

}
