package com.blueharvest.demo.service.functional;

import com.blueharvest.demo.exception.NotFoundException;
import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.User;
import com.blueharvest.demo.service.entity.AccountService;
import com.blueharvest.demo.service.entity.UserService;
import com.blueharvest.demo.service.functional.AccountsTransactionsService;
import com.blueharvest.demo.service.functional.SecondaryAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SecondaryAccountServiceTest {

    @InjectMocks private SecondaryAccountService secondaryAccountService;

    @Mock private UserService userService;
    @Mock private AccountService accountService;
    @Mock private AccountsTransactionsService accountsTransactionsService;

    @Test(expected = NotFoundException.class)
    public void createSecondaryAccountForUser__user_is_null__throws_not_found_exception(){
        when(userService.getUserById(anyLong())).thenReturn(null);

        secondaryAccountService.createSecondaryAccountForUser(1L, BigDecimal.ZERO);
    }

    @Test
    public void createSecondaryAccountForUser__user_is_not_null_initial_credit_is_ZERO(){
        User user = new User();

        when(userService.getUserById(anyLong())).thenReturn(user);

        User resultUser = secondaryAccountService.createSecondaryAccountForUser(1L, BigDecimal.ZERO);

        assertThat(resultUser.getAccounts()).isNotNull();
        assertThat(resultUser.getAccounts().get(0)).isNotNull();
        verify(accountService, times(1)).saveAccount(any(Account.class));
        verify(userService, times(1)).saveUser(user);
    }

    @Test
    public void createSecondaryAccountForUser__user_is_not_null_initial_credit_is_not_ZERO(){
        User user = new User();
        Account primaryAccount = new Account();
        primaryAccount.setId(1L);
        primaryAccount.setAccountBalance(BigDecimal.valueOf(1000));

        when(userService.getUserById(anyLong())).thenReturn(user);
        when(accountService.findPrimaryAccountInUserAccounts(anyList())).thenReturn(primaryAccount);

        User resultUser = secondaryAccountService.createSecondaryAccountForUser(1L, BigDecimal.valueOf(100));

        assertThat(resultUser.getAccounts()).isNotNull();
        assertThat(resultUser.getAccounts().get(0)).isNotNull();
        verify(accountsTransactionsService, times(1)).transactionBetweenAccounts(any(Account.class), any(Account.class), any(BigDecimal.class));
        verify(accountService, times(2)).saveAccount(any(Account.class));
        verify(userService, times(1)).saveUser(user);
    }
}
