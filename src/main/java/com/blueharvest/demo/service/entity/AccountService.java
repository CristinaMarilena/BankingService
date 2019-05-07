package com.blueharvest.demo.service.entity;

import com.blueharvest.demo.model.Account;

import java.util.List;

public interface AccountService {

    Account findById(Long id);
    Account saveAccount(Account account);
    Account findPrimaryAccountInUserAccounts(List<Long> accountIds);
}
