package com.blueharvest.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long fromAccount;
    private Long toAccount;
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Long fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Long getToAccount() {
        return toAccount;
    }

    public void setToAccount(Long toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
