package com.soge.katas.accounts;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soge.katas.accounts.exceptions.NonSufficientFundsException;
import com.soge.katas.accounts.transactions.Transaction;
import com.soge.katas.accounts.transactions.Transactions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountShould {

    private Account account;
    private BigDecimal openingAmount;
    private Transactions transactions;

    @BeforeEach
    void init(@Mock Transactions transactions) {
        this.transactions = transactions;
        openingAmount = BigDecimal.valueOf(100L);
        account = new Account(openingAmount, transactions);
    }

    @Test
    public void init_a_transaction_with_a_positive_amount_for_a_deposit() {
        when(transactions.lastBalance()).thenReturn(BigDecimal.valueOf(50L));
        account.deposit(BigDecimal.valueOf(100L));
        verify(transactions, times(2)).add(any(Transaction.class));
    }

    @Test
    public void init_a_transaction_with_a_negative_amount_for_a_deposit() {
        assertThrows(
                IllegalArgumentException.class,
                () -> account.deposit(BigDecimal.valueOf(-100L)));
    }

    @Test
    public void init_a_transaction_with_a_null_amount_for_a_deposit() {
        assertThrows(
                IllegalArgumentException.class,
                () -> account.deposit(BigDecimal.ZERO));
    }

    @Test
    public void init_a_transaction_with_a_positive_amount_less_than_the_balance_for_a_withdraw()
            throws NonSufficientFundsException {
        when(transactions.lastBalance()).thenReturn(openingAmount);
        account.withdraw(BigDecimal.valueOf(50L));
        verify(transactions, times(2)).add(any(Transaction.class));
    }

    @Test
    public void init_a_transaction_with_a_positive_amount_equals_to_the_balance_for_a_withdraw()
            throws NonSufficientFundsException {
        when(transactions.lastBalance()).thenReturn(openingAmount);
        account.withdraw(openingAmount);
        verify(transactions, times(2)).add(any(Transaction.class));
    }

    @Test
    public void init_a_transaction_with_a_positive_amount_greater_than_the_balance_for_a_withdraw() {
        when(transactions.lastBalance()).thenReturn(openingAmount);
        assertThrows(
                NonSufficientFundsException.class,
                () -> account.withdraw(openingAmount.add(BigDecimal.TEN)));
    }

    @Test
    public void init_a_transaction_with_a_negative_amount_for_a_withdraw() {
        assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(BigDecimal.valueOf(-100L)));
    }

    @Test
    public void init_a_transaction_with_a_null_amount_for_a_withdraw() {
        assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(BigDecimal.ZERO));
    }
}
