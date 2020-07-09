package com.soge.katas.accounts;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soge.katas.accounts.transactions.Transaction;
import com.soge.katas.accounts.transactions.Transactions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountShould {

    private Account account;
    private Transactions transactions;


    @BeforeEach
    void init(@Mock Transactions transactions) {
        this.transactions = transactions;
        account = new Account(BigDecimal.valueOf(100L), transactions);
    }

    @Test
    public void init_a_transaction_with_a_positive_amount_for_a_deposit() {
        when(transactions.lastBalance()).thenReturn(BigDecimal.valueOf(50L));
        account.deposit(BigDecimal.valueOf(100L));
        verify(transactions, atLeastOnce()).add(any(Transaction.class));
    }

    @Test
    public void init_a_transaction_with_a_negative_amount_for_a_deposit() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> account.deposit(BigDecimal.valueOf(-100L)));
    }

    @Test
    public void init_a_transaction_with_a_null_amount_for_a_deposit() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> account.deposit(BigDecimal.ZERO));
    }
}
