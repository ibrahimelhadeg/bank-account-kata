package com.soge.katas.accounts.transactions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    public enum Type {
        DEPOSIT,
        WITHDRAWAL
    }

    private final Type type;
    private final LocalDateTime time;
    private final BigDecimal amount;
    private final BigDecimal balance;

    public Transaction(
            final Type type, final LocalDateTime time,
            final BigDecimal amount, final BigDecimal balance) {
        this.type = type;
        this.time = time;
        this.amount = amount;
        this.balance = balance;
    }

    public String type() {
        return type.toString();
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
