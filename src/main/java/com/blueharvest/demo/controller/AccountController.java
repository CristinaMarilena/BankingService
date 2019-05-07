package com.blueharvest.demo.controller;

import com.blueharvest.demo.dto.UserAccountsDto;
import com.blueharvest.demo.converter.UserConverter;
import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.User;
import com.blueharvest.demo.service.entity.AccountService;
import com.blueharvest.demo.service.functional.SecondaryAccountService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private SecondaryAccountService secondaryAccountService;
    private UserConverter userConverter;
    private AccountService accountService;

    @Inject
    public AccountController(SecondaryAccountService secondaryAccountService,  UserConverter userConverter, AccountService accountService){
        this.secondaryAccountService = secondaryAccountService;
        this.userConverter = userConverter;
        this.accountService = accountService;
    }

    @PostMapping("/{customerId}/{initialCredit}")
    public UserAccountsDto createSecondaryAccount(@PathVariable Long customerId, @PathVariable BigDecimal initialCredit){
        User user = this.secondaryAccountService.createSecondaryAccountForUser(customerId, initialCredit);
        return userConverter.convertToUserAccountsDto(user);
    }

    @GetMapping("/{id}")
    public Account get(@PathVariable Long id){
        return accountService.findById(id);
    }

/*    @PostMapping("/{id}")
    public Account updateAccount(@RequestBody Account account){
        return accountService.updateAccount(account);
    }*/

}
