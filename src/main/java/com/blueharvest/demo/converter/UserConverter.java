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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserConverter.class);

    @Inject
    public UserConverter(ModelMapper modelMapper, AccountService accountService, TransactionService transactionService) {
        this.modelMapper = modelMapper;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    public UserAccountsDto convertToUserAccountsDto(User user) {
        UserAccountsDto userAccountsDto = modelMapper.map(user, UserAccountsDto.class);
        List<Account> accounts = user.getAccounts();
        List<Account> userAccounts = new ArrayList<>(accounts);
        userAccountsDto.setAccounts(userAccounts);
        return userAccountsDto;
    }

    public UserDto convertToUserDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        List<AccountTransactionsSummary> userAccountsTransactions = new ArrayList<>();
        List<Account> userAccounts = user.getAccounts();
        BigDecimal userBalance = BigDecimal.ZERO;

        if (userAccounts != null) {
            for (Account userAccount : userAccounts) {
                AccountTransactionsSummary accountTransactionsSummary = buildTransactionSummeryOfAccount(userAccount.getId(), userAccount);
                userAccountsTransactions.add(accountTransactionsSummary);

                if (userAccount.getAccountBalance() == null) {
                    userAccount.setAccountBalance(BigDecimal.ZERO);
                    accountService.updateAccount(userAccount);
                } else {
                    userBalance = userBalance.add(userAccount.getAccountBalance());
                }
            }
        }
        userDto.setBalance(userBalance);
        userDto.setAccounts(userAccountsTransactions);
        return userDto;
    }

    private AccountTransactionsSummary buildTransactionSummeryOfAccount(Long accountId, Account account) {
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
