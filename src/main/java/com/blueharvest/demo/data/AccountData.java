package com.blueharvest.demo.data;

import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.AccountType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AccountData {

    private volatile static AccountData instance;
    private List<Account> accounts;

    private AccountData() {
        addAccountsToTheInitialList();
    }

    public static AccountData getInstance() {
        if (instance == null) {
            synchronized (AccountData.class) {
                if (instance == null) {
                    instance = new AccountData();
                }
            }
        }

        return instance;
    }

    /**********************************************
     */

    public List<Account> getAccounts() {
        return accounts;
    }

    public Account findAccountById(Long id) {
        Optional<Account> optionalAccount = this.accounts.stream().filter(user -> user.getId().equals(id)).findFirst();
        return optionalAccount.orElse(null);
    }

    public Account addAccount(Account account) {
        this.accounts.add(account);
        return account;
    }

    public void deleteAccount(Long id){
        int accountIndex = 0;
        for(int i = 0; i < this.accounts.size() ; i++){
            if(accounts.get(i).getId().equals(id)){
                accountIndex = i;
            }
        }

        this.accounts.remove(accountIndex);
    }

    public Account findAccountIsPrimary(List<Long> ids){
        Account primaryAccount = null;

        for(int i = 0; i < this.accounts.size() ; i++){
            for(Long id:ids){
                if(accounts.get(i).getId().equals(id) && accounts.get(i).isPrimary()){
                    primaryAccount = accounts.get(i);
                }
            }
        }

        return primaryAccount;
    }

    public Account updateAccount(Account accountToBeUpdated){
        this.deleteAccount(accountToBeUpdated.getId());
        this.addAccount(accountToBeUpdated);
        return this.findAccountById(accountToBeUpdated.getId());
    }

    /*********************************************************
     */

    private void addAccountsToTheInitialList() {
        this.accounts = new ArrayList<>();

        Account account1 = new Account();
        account1.setId(1L);
        account1.setPrimary(true);
        account1.setAccountType(AccountType.STANDARD);
        account1.setAccountBalance(BigDecimal.valueOf(700));
        this.accounts.add(account1);

        Account account2 = new Account();
        account2.setId(2L);
        account2.setPrimary(false);
        account2.setAccountType(AccountType.SAVINGS);
        this.accounts.add(account2);

        Account account3 = new Account();
        account3.setId(3L);
        this.accounts.add(account3);

        Account account4 = new Account();
        account4.setId(4L);
        this.accounts.add(account4);

        Account account5 = new Account();
        account5.setId(5L);
        this.accounts.add(account5);

        Account account6 = new Account();
        account6.setId(6L);
        this.accounts.add(account6);

        Account account7 = new Account();
        account7.setId(7L);
        this.accounts.add(account7);
    }

}
