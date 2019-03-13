package com.blueharvest.demo.dto;

import com.blueharvest.demo.model.AccountTransactionsSummary;

import java.math.BigDecimal;
import java.util.List;

public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private BigDecimal balance;
    private List<AccountTransactionsSummary> accounts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<AccountTransactionsSummary> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountTransactionsSummary> accounts) {
        this.accounts = accounts;
    }
}
