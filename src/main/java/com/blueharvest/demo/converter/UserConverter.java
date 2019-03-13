package com.blueharvest.demo.converter;

import com.blueharvest.demo.dto.UserAccountsDto;
import com.blueharvest.demo.dto.UserDto;
import com.blueharvest.demo.exception.NotFoundException;
import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.AccountTransactionsSummary;
import com.blueharvest.demo.model.Transaction;
import com.blueharvest.demo.model.User;
import com.blueharvest.demo.service.entity.AccountService;
import com.blueharvest.demo.service.entity.TransactionService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger LOGGER = LoggerFactory.getLogger(UserConverter.class);

    @Inject
    public UserConverter(ModelMapper modelMapper, AccountService accountService, TransactionService transactionService) {
        this.modelMapper = modelMapper;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    public UserAccountsDto convertToUserAccountsDto(User user) {
        UserAccountsDto userAccountsDto = modelMapper.map(user, UserAccountsDto.class);
        List<Long> accounts = user.getAccounts();
        List<Account> userAccounts = new ArrayList<>();

        for (Long accountId : accounts) {
            Account account = accountService.getAccountById(accountId);
            userAccounts.add(account);
        }

        userAccountsDto.setAccounts(userAccounts);
        return userAccountsDto;
    }

    public UserDto convertToUserDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);

        List<AccountTransactionsSummary> userAccountsTransactions = new ArrayList<>();
        List<Long> userAccounts = user.getAccounts();
        BigDecimal userBalance = BigDecimal.ZERO;

        if (userAccounts != null) {
            for (Long accountId : userAccounts) {

                Account account = accountService.getAccountById(accountId);
                AccountTransactionsSummary accountTransactionsSummary = buildAccountTransactionSummeryOfAccount(accountId, account);
                userAccountsTransactions.add(accountTransactionsSummary);

                if (account.getAccountBalance() == null) {
                    account.setAccountBalance(BigDecimal.ZERO);
                    accountService.updateAccount(account);
                } else {
                    userBalance = userBalance.add(account.getAccountBalance());
                }
            }
        }

        userDto.setBalance(userBalance);
        userDto.setAccounts(userAccountsTransactions);
        return userDto;
    }

    private AccountTransactionsSummary buildAccountTransactionSummeryOfAccount(Long accountId, Account account) {
        List<Transaction> transactions = transactionService.findByAccount(accountId);

        if(transactions == null){
            LOGGER.warn("No transactions found of the account with nr : " + accountId);
            throw new NotFoundException("No transactions of the account with nr : " + accountId + ". Please contact the administration");
        }

        AccountTransactionsSummary accountTransactionsSummary = new AccountTransactionsSummary();
        accountTransactionsSummary.setAccountBalance(account.getAccountBalance());
        accountTransactionsSummary.setAccountId(accountId);
        accountTransactionsSummary.setAccountType(account.getAccountType());
        accountTransactionsSummary.setTransactions(transactions);

        return accountTransactionsSummary;
    }
}
