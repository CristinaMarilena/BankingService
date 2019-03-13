package com.blueharvest.demo.controller;

import com.blueharvest.demo.dto.UserAccountsDto;
import com.blueharvest.demo.converter.UserConverter;
import com.blueharvest.demo.model.User;
import com.blueharvest.demo.service.functional.SecondaryAccountService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
public class AccountController {

    private SecondaryAccountService secondaryAccountService;
    private UserConverter userConverter;

    @Inject
    public AccountController(SecondaryAccountService secondaryAccountService,  UserConverter userConverter){
        this.secondaryAccountService = secondaryAccountService;
        this.userConverter = userConverter;
    }

    @PostMapping("/{customerId}/{initialCredit}")
    public UserAccountsDto createSecondaryAccount(@PathVariable Long customerId, @PathVariable BigDecimal initialCredit){
        User user = this.secondaryAccountService.createSecondaryAccountForUser(customerId, initialCredit);
        return userConverter.convertToUserAccountsDto(user);
    }

}
