package com.soge.katas.accounts;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.soge.katas.accounts.transactions.Transaction;
import com.soge.katas.accounts.transactions.Transactions;

public class Account {

    private final Transactions transactions;

    public Account(final BigDecimal openingAmount, final Transactions transactions) {
        if (openingAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cannot create account with negative balance");
        }
        this.transactions = transactions;
        this.transactions.add(new Transaction(LocalDateTime.now(), openingAmount, openingAmount));
    }

    public void deposit(final BigDecimal howMuch) {
        if (howMuch.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Cannot deposit a negative or null amount");
        }
        BigDecimal previousBalance = transactions.lastBalance();
        Transaction transaction = new Transaction(
                LocalDateTime.now(), howMuch, previousBalance.add(howMuch) );
        transactions.add(transaction);
    }
}
