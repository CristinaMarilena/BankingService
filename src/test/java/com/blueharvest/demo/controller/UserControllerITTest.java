package com.blueharvest.demo.controller;

import com.blueharvest.demo.dto.UserDto;
import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.Transaction;
import com.blueharvest.demo.model.User;
import com.blueharvest.demo.service.entity.AccountService;
import com.blueharvest.demo.service.entity.TransactionService;
import com.blueharvest.demo.service.entity.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.blueharvest.demo.model.AccountType.PRIMARY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerITTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private AccountService accountService;

    @MockBean
    private TransactionService transactionService;

    private ObjectMapper mapper;

    @Before
    public void setup(){
        mapper = new ObjectMapper();
    }

    @Test
    public void getUserInformation__id_of_user_is_not_found() throws Exception {
        when(userService.getUserById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/user/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getUserInformation__id_of_user_is_found() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("userName");
        user.setSurname("userSurname");

        Account account = new Account();
        account.setId(1L);
        account.setAccountBalance(BigDecimal.valueOf(1000));
        account.setAccountType(PRIMARY.toString());
        account.setPrimary(true);

        List<Account> accounts = new ArrayList<>();
        accounts.add(account);

        List<Transaction> accountTransactions = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(200));
        Account accountFrom = new Account();
        accountFrom.setId(1L);
        transaction.setFromAccount(accountFrom);
        Account accountTo= new Account();
        accountTo.setId(2136L);
        transaction.setToAccount(accountTo);
        transaction.setId(122L);

        Transaction transaction1 = new Transaction();
        transaction1.setAmount(BigDecimal.valueOf(500));
        Account accountFrom1 = new Account();
        accountFrom1.setId(74632L);
        transaction.setFromAccount(accountFrom1);
        Account accountTo1= new Account();
        accountTo1.setId(3777L);
        transaction.setToAccount(accountTo1);
        transaction.setId(3777L);

        accountTransactions.add(transaction);
        accountTransactions.add(transaction1);

        user.setAccounts(accounts);

        when(userService.getUserById(anyLong())).thenReturn(user);
        when(accountService.findById(anyLong())).thenReturn(account);
        when(transactionService.findByAccount(any(Account.class))).thenReturn(accountTransactions);

         mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(mvcResult -> {
                    UserDto userDto = mapper.readValue(mvcResult.getResponse().getContentAsString(), UserDto.class);
                    assertThat(userDto).isNotNull();
                    assertThat(userDto.getName()).isEqualTo("userName");
                    assertThat(userDto.getSurname()).isEqualTo("userSurname");
                    assertThat(userDto.getBalance()).isEqualTo(BigDecimal.valueOf(1000));
                    assertThat(userDto.getAccounts()).isNotNull();
                    assertThat(userDto.getAccounts().get(0)).isNotNull();
                    assertThat(userDto.getAccounts().get(0).getAccountBalance()).isEqualTo(BigDecimal.valueOf(1000));
                    assertThat(userDto.getAccounts().get(0).getAccountId()).isEqualTo(1L);
                    assertThat(userDto.getAccounts().get(0).getAccountType()).isEqualTo(PRIMARY.toString());
                    assertThat(userDto.getAccounts().get(0).getTransactions()).isNotNull();
                    assertThat(userDto.getAccounts().get(0).getTransactions().get(0)).isNotNull();
                    assertThat(userDto.getAccounts().get(0).getTransactions().get(0).getId()).isNotNull();
                    assertThat(userDto.getAccounts().get(0).getTransactions().get(0).getId()).isEqualTo(3777L);
                    assertThat(userDto.getAccounts().get(0).getTransactions().get(0).getFromAccount()).isNotNull();
                    assertThat(userDto.getAccounts().get(0).getTransactions().get(0).getFromAccount().getId()).isEqualTo(74632L);
                    assertThat(userDto.getAccounts().get(0).getTransactions().get(0).getToAccount()).isNotNull();
                    assertThat(userDto.getAccounts().get(0).getTransactions().get(0).getToAccount().getId()).isEqualTo(3777L);
                    assertThat(userDto.getAccounts().get(0).getTransactions().get(1)).isNotNull();
                }).andReturn();
    }

}
