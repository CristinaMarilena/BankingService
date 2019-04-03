package com.blueharvest.demo.converter;

import com.blueharvest.demo.dto.UserDto;
import com.blueharvest.demo.exception.NotFoundException;
import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.Transaction;
import com.blueharvest.demo.model.User;
import com.blueharvest.demo.service.entity.AccountService;
import com.blueharvest.demo.service.entity.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserConverterTest {

    @InjectMocks
    private UserConverter userConverter;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private AccountService accountService;

    @Mock
    private TransactionService transactionService;

    @Test
    public void convertToUserDto__user_doesnt_have_accounts(){
        UserDto userDto = new UserDto();
        userDto.setName("name");
        userDto.setSurname("surname");

        when(modelMapper.map(any(), any())).thenReturn(userDto);

        userDto = userConverter.convertToUserDto(new User());

        assertThat(userDto.getBalance()).isEqualTo(BigDecimal.ZERO);
        assertThat(userDto.getAccounts()).isEmpty();
        assertThat(userDto.getName()).isEqualTo("name");
        assertThat(userDto.getSurname()).isEqualTo("surname");
    }

    @Test
    public void convertToUserDto__user_has_one_account(){
        UserDto userDto = new UserDto();
        userDto.setName("name");
        userDto.setSurname("surname");

        User user = new User();


        Account account = new Account();
        account.setAccountBalance(BigDecimal.valueOf(300));
        account.setId(1L);

        List<Account> userAccounts = new ArrayList<>();
        userAccounts.add(account);
        user.setAccounts(userAccounts);


        Transaction transaction = new Transaction();
        transaction.setFromAccount(1L);
        transaction.setToAccount(21L);
        transaction.setAmount(BigDecimal.valueOf(200));

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        when(modelMapper.map(any(), any())).thenReturn(userDto);
        when(accountService.getAccountById(anyLong())).thenReturn(account);
        when(transactionService.findByAccount(anyLong())).thenReturn(transactions);

        userDto = userConverter.convertToUserDto(user);

        assertThat(userDto.getBalance()).isEqualTo(BigDecimal.valueOf(300));
        assertThat(userDto.getName()).isEqualTo("name");
        assertThat(userDto.getSurname()).isEqualTo("surname");
        assertThat(userDto.getAccounts()).isNotNull();
        assertThat(userDto.getAccounts().size()).isEqualTo(1);
        assertThat(userDto.getAccounts().get(0)).isNotNull();
        assertThat(userDto.getAccounts().get(0).getTransactions()).isNotNull();
        assertThat(userDto.getAccounts().get(0).getTransactions().size()).isEqualTo(1);
        assertThat(userDto.getAccounts().get(0).getTransactions().get(0)).isNotNull();
        assertThat(userDto.getAccounts().get(0).getTransactions().get(0).getAmount()).isEqualTo(BigDecimal.valueOf(200));
        assertThat(userDto.getAccounts().get(0).getTransactions().get(0).getFromAccount()).isEqualTo(1L);
        assertThat(userDto.getAccounts().get(0).getTransactions().get(0).getToAccount()).isEqualTo(21L);
    }

    @Test(expected = NotFoundException.class)
    public void convertToUserDto__user_has_one_account_no_transactions__not_found_exception_thrown(){
        UserDto userDto = new UserDto();
        userDto.setName("name");
        userDto.setSurname("surname");

        User user = new User();

        Account account = new Account();
        account.setAccountBalance(BigDecimal.valueOf(300));
        account.setId(1L);

        List<Account> userAccounts = new ArrayList<>();
        userAccounts.add(account);
        user.setAccounts(userAccounts);

        when(modelMapper.map(any(), any())).thenReturn(userDto);
        when(accountService.getAccountById(anyLong())).thenReturn(account);
        when(transactionService.findByAccount(anyLong())).thenReturn(null);

        userConverter.convertToUserDto(user);
    }
}
