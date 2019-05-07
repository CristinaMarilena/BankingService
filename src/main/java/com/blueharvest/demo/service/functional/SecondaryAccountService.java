package com.blueharvest.demo.service.functional;

import com.blueharvest.demo.exception.NotFoundException;
import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.User;
import com.blueharvest.demo.service.entity.AccountService;
import com.blueharvest.demo.service.entity.UserService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.blueharvest.demo.model.AccountType.SAVINGS;
import static com.blueharvest.demo.utils.AccountUtils.createSimpleAccount;

@Service
public class SecondaryAccountService {

    private final UserService userService;
    private AccountService accountService;
    private AccountsTransactionsService accountsTransactionsService;

    @Inject
    public SecondaryAccountService(UserService userService, AccountService accountService, AccountsTransactionsService accountsTransactionsService){
        this.userService = userService;
        this.accountService = accountService;
        this.accountsTransactionsService = accountsTransactionsService;
    }

    public User createSecondaryAccountForUser(Long userId, BigDecimal initialCredit){
        User user = userService.getUserById(userId);
        if(user == null) {
            throw new NotFoundException("The user was not found.Please contact the administration.");
        }

        Account account = createSimpleAccount(false, SAVINGS);
        user.addAccount(account);

        if(initialCredit.compareTo(BigDecimal.ZERO) > 0){
            List<Long> accountsIds = new ArrayList<>();
            user.getAccounts().forEach(userAccount -> accountsIds.add(userAccount.getId()));

            Account primaryAccount = accountService.findPrimaryAccountInUserAccounts(accountsIds);
            transactionBetweenPrimaryAndCurrentAccount(primaryAccount, account, initialCredit);
        }
        accountService.saveAccount(account);
        userService.saveUser(user);
        return user;
    }

    private void transactionBetweenPrimaryAndCurrentAccount(Account primaryAccount, Account account, BigDecimal initialCredit){
        accountService.saveAccount(account);
        accountsTransactionsService.transactionBetweenAccounts(primaryAccount, account, initialCredit);
    }

}
