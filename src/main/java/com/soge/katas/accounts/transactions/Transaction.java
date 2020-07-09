package com.soge.katas.accounts.transactions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private final LocalDateTime time;
    private final BigDecimal amount;
    private final BigDecimal balance;

    public Transaction(final LocalDateTime time, final BigDecimal amount, final BigDecimal balance) {
        this.time = time;
        this.amount = amount;
        this.balance = balance;
    }

    public LocalDateTime time() {
        return time;
    }

    public BigDecimal amount() {
        return amount;
    }

    public BigDecimal balance() {
        return balance;
    }
}
