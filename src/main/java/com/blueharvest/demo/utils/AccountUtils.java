package com.blueharvest.demo.utils;

import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.AccountType;

import java.math.BigDecimal;

import static com.blueharvest.demo.utils.RandomGenerator.randomId;

public class AccountUtils {

    public static Account createSimpleAccount(boolean primary, AccountType accountType){
        Account account = new Account();
        account.setId(randomId());
        account.setPrimary(primary);
        account.setAccountType(accountType);
        account.setAccountBalance(BigDecimal.ZERO);
        return account;
    }
}
