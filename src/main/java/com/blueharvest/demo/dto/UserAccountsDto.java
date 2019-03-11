package com.blueharvest.demo.dto;

import com.blueharvest.demo.model.Account;

import java.util.List;

public class UserAccountsDto {

    private String username;
    private List<Account> accounts;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
