package com.blueharvest.demo.dto;

import com.blueharvest.demo.model.Account;

public class UserPrimaryAccountDto {

    private String username;
    private Account primaryAccount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Account getPrimaryAccount() {
        return primaryAccount;
    }

    public void setPrimaryAccount(Account primaryAccount) {
        this.primaryAccount = primaryAccount;
    }
}