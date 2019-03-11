package com.blueharvest.demo.service.entity;

import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.AccountType;

import java.util.List;

public interface AccountService {

    Account getAccountById(Long id);
    Account saveAccount(Account account);
    Account updateAccount(Account account);
    Account findPrimaryAccountInUserAccounts(List<Long> accountIds);
    Account createSimpleAccount(boolean primary, AccountType accountType);
}
