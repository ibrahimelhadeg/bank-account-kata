package com.soge.katas.accounts;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soge.katas.accounts.transactions.Transaction;
import com.soge.katas.accounts.transactions.Transactions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountCreation {

    @Mock
    private Transactions transactions;

    @Test
    public void create_an_account_with_a_positive_initial_amount() {
        BigDecimal initAmount = BigDecimal.valueOf(100L);
        new Account(initAmount, transactions);
        verify(transactions).add(any(Transaction.class));
    }

    @Test
    public void create_an_account_with_a_null_initial_amount() {
        BigDecimal initAmount = BigDecimal.valueOf(0L);
        new Account(initAmount, transactions);
        verify(transactions).add(any(Transaction.class));
    }

    @Test
    public void create_an_account_with_a_negative_initial_amount() {
        BigDecimal initAmount = BigDecimal.valueOf(-100L);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Account(initAmount, transactions));
    }
}
