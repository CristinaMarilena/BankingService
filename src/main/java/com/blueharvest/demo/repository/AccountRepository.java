package com.blueharvest.demo.repository;

import com.blueharvest.demo.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.isPrimary = 1 and id IN (?1)")
    public Account findByIsPrimary(List<Long> accountIds);

}
