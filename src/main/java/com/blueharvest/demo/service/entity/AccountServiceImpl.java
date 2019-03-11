package com.blueharvest.demo.service.entity;

import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.AccountType;
import com.blueharvest.demo.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

import static com.blueharvest.demo.utils.RandomGenerator.randomId;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Inject
    AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Account account) {
        return accountRepository.updateAcount(account);
    }

    @Override
    public Account findPrimaryAccountInUserAccounts(List<Long> accountIds) {
        return accountRepository.findByIsPrimary(accountIds);
    }

    public Account createSimpleAccount(boolean primary, AccountType accountType){
        Account account = new Account();
        account.setId(randomId());
        account.setPrimary(primary);
        account.setAccountType(accountType);
        account.setAccountBalance(BigDecimal.ZERO);
        return account;
    }
}
