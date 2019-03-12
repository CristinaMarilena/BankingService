package com.blueharvest.demo.mapping;

import com.blueharvest.demo.dto.UserAccountsDto;
import com.blueharvest.demo.dto.UserDto;
import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.AccountTransactionsSummary;
import com.blueharvest.demo.model.Transaction;
import com.blueharvest.demo.model.User;
import com.blueharvest.demo.service.entity.AccountService;
import com.blueharvest.demo.service.entity.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    private ModelMapper modelMapper;
    private AccountService accountService;
    private TransactionService transactionService;

    @Inject
    public UserConverter( ModelMapper modelMapper, AccountService accountService, TransactionService transactionService){
        this.modelMapper = modelMapper;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    public UserAccountsDto convertToUserAccountsDto(User user){
        UserAccountsDto userAccountsDto = modelMapper.map(user, UserAccountsDto.class);
        List<Long> accounts = user.getAccounts();
        List<Account> userAccounts = new ArrayList<>();

        for (Long accountId: accounts){
            Account account = accountService.getAccountById(accountId);
            userAccounts.add(account);
        }

        userAccountsDto.setAccounts(userAccounts);
        return userAccountsDto;
    }

    public UserDto convertToUserDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);

        List<AccountTransactionsSummary> userAccounts = new ArrayList<>();
        List<Long> accounts = user.getAccounts();
        BigDecimal balance = BigDecimal.ZERO;

        for (Long accountId: accounts){
            Account account = accountService.getAccountById(accountId);
            List<Transaction> transactions = transactionService.findByAccount(accountId);

            //TODO make a builder pls pls pls
            AccountTransactionsSummary accountTransactionsSummary = new AccountTransactionsSummary();
            accountTransactionsSummary.setAccountBalance(account.getAccountBalance());
            accountTransactionsSummary.setAccountId(accountId);
            accountTransactionsSummary.setAccountType(account.getAccountType());
            accountTransactionsSummary.setTransactions(transactions);

            userAccounts.add(accountTransactionsSummary);

            balance = balance.add(account.getAccountBalance());

        }

        userDto.setBalance(balance);
        userDto.setAccounts(userAccounts);
        return userDto;
    }
}
