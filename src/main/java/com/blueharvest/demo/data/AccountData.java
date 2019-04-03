package com.blueharvest.demo.data;

import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.AccountType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AccountData {

    private List<Account> accounts;

    private AccountData() {
        addAccountsToTheInitialList();
    }

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

    private void addAccountsToTheInitialList() {
        this.accounts = new ArrayList<>();

        Account account1 = new Account();
        account1.setId(1L);
        account1.setPrimary(true);
        account1.setAccountType(AccountType.PRIMARY.toString());
        account1.setAccountBalance(BigDecimal.valueOf(1000));
        this.accounts.add(account1);
    }

}
