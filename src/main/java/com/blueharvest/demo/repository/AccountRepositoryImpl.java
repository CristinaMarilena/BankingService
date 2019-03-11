package com.blueharvest.demo.repository;

import com.blueharvest.demo.data.AccountData;
import com.blueharvest.demo.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private AccountData accountData;

    public AccountRepositoryImpl(){
        this.accountData = AccountData.getInstance();
    }

    @Override
    public Account findById(Long id) {
        return accountData.findAccountById(id);
    }

    @Override
    public Account save(Account account) {
        return accountData.addAccount(account);
    }

    @Override
    public Account updateAcount(Account account) {
        return accountData.updateAccount(account);
    }

    @Override
    public Account findByIsPrimary(List<Long> accountsIds) {
        return accountData.findAccountIsPrimary(accountsIds);
    }
}
