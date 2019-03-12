package com.blueharvest.demo.repository;

import com.blueharvest.demo.model.Account;

import java.util.List;

public interface AccountRepository {

    Account findById(Long id);
    Account save(Account account);
    Account updateAccount(Account account);
    Account findByIsPrimary(List<Long> accountsIds);
}
