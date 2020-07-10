package com.soge.katas.accounts;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.soge.katas.accounts.exceptions.NonSufficientFundsException;
import com.soge.katas.accounts.transactions.Transaction;
import com.soge.katas.accounts.transactions.Transactions;

public class Account {

    private final Transactions transactions;

    public Account(final BigDecimal openingAmount, final Transactions transactions) {
        if (openingAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cannot create account with negative balance");
        }
        this.transactions = transactions;
        this.recordTransactionOf(
                Transaction.Type.DEPOSIT,
                openingAmount,
                openingAmount);
    }

    public void deposit(final BigDecimal howMuch) {
        if (howMuch.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Cannot deposit a negative or null amount");
        }
        BigDecimal previousBalance = transactions.lastBalance();
        recordTransactionOf(
                Transaction.Type.DEPOSIT,
                howMuch,
                previousBalance.add(howMuch));
    }

    public void withdraw(final BigDecimal howMuch) throws NonSufficientFundsException {
        if (howMuch.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Cannot withdraw a negative or null amount");
        }
        BigDecimal previousBalance = transactions.lastBalance();
        if(previousBalance.compareTo(howMuch) < 0) {
            throw new NonSufficientFundsException("Insufficient funds");
        }
        recordTransactionOf(
                Transaction.Type.WITHDRAWAL,
                howMuch.multiply(BigDecimal.valueOf(-1L)),
                previousBalance.subtract(howMuch));
    }

    private void recordTransactionOf(Transaction.Type type, BigDecimal howMuch, BigDecimal balance) {
        Transaction transaction = new Transaction(type, LocalDateTime.now(), howMuch, balance);
        transactions.add(transaction);
    }
}
