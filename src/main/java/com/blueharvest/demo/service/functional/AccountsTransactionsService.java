package com.blueharvest.demo.service.functional;

import com.blueharvest.demo.exception.InsufficientFundException;
import com.blueharvest.demo.exception.NotFoundException;
import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.Transaction;
import com.blueharvest.demo.service.entity.AccountService;
import com.blueharvest.demo.service.entity.TransactionService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;

@Service
public class AccountsTransactionsService {

    private AccountService accountService;
    private TransactionService transactionService;

    @Inject
    public AccountsTransactionsService(AccountService accountService, TransactionService transactionService){
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    public void transactionBetweenAccounts(Account fromAccount, Account toAccount, BigDecimal credit){

        if(fromAccount == null){
            throw new NotFoundException("The user doesn't have a primary account.Please contact the administration.");
        }

        if(fromAccount.getAccountBalance().compareTo(credit) < 0){
            throw new InsufficientFundException("Not enough credit for this transaction.");
        }

        Transaction transaction = transactionService.createSimpleTransaction(fromAccount, toAccount, credit);

        fromAccount.setAccountBalance(fromAccount.getAccountBalance().subtract(credit));
        accountService.saveAccount(fromAccount);

        toAccount.setAccountBalance(credit);
        accountService.saveAccount(toAccount);

        transactionService.saveTransaction(transaction);
    }

}
