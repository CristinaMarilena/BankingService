package com.blueharvest.demo.controller;

import com.blueharvest.demo.model.Account;
import com.blueharvest.demo.model.Transaction;
import com.blueharvest.demo.service.entity.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Inject
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public Transaction get(@PathVariable Long id){
        return transactionService.getTransactionById(id);
    }
}
