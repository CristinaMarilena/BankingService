package com.blueharvest.demo.mapping;

import com.blueharvest.demo.dto.UserPrimaryAccountDto;
import com.blueharvest.demo.dto.UserAccountsDto;
import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.User;
import com.blueharvest.demo.service.entity.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    private ModelMapper modelMapper;
    private AccountService accountService;

    @Inject
    public UserConverter( ModelMapper modelMapper, AccountService accountService){
        this.modelMapper = modelMapper;
        this.accountService = accountService;
    }

    public UserPrimaryAccountDto convertToUserPrimaryAccountDto(User user){
        UserPrimaryAccountDto userPrimaryAccountDto = modelMapper.map(user, UserPrimaryAccountDto.class);
        List<Long> accounts = user.getAccounts();

        for (Long accountId: accounts){
            Account account = accountService.getAccountById(accountId);
            if(account.isPrimary()){
                userPrimaryAccountDto.setPrimaryAccount(account);
            }
        }
        return userPrimaryAccountDto;
    }

    public UserAccountsDto convertToUserAccountsDto(User user){
        UserAccountsDto userAccountsDto = modelMapper.map(user, UserAccountsDto.class);
        List<Long> accounts = user.getAccounts();
        List<Account> userAccounts = new ArrayList<>();

        for (Long accountId: accounts){
            Account account = accountService.getAccountById(accountId);
            userAccounts.add(account);
        }

        userAccountsDto.setAccounts(userAccounts);
        return userAccountsDto;
    }
}
