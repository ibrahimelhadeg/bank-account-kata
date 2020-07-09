package com.soge.katas.accounts.transactions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class InMemoryTransactionsShould {

    private InMemoryTransactions transactions;

    @BeforeEach
    public void setUp() {
        transactions = new InMemoryTransactions();
    }

    @Test
    public void add_a_transaction() {
        Transaction transaction = new Transaction(
                LocalDateTime.of(2020, 7, 9, 15, 0),
                BigDecimal.valueOf(100L), BigDecimal.valueOf(100L));
        transactions.add(transaction);
        assertTrue(transactions.transactions().contains(transaction));
    }

    @Test
    public void get_the_last_balance() {

        LocalDateTime transactionOneTime = LocalDateTime.of(2020, 7, 9, 15, 0);
        BigDecimal transactionOneAmount = BigDecimal.valueOf(100L);
        Transaction transactionOne = new Transaction(
                transactionOneTime, transactionOneAmount, transactionOneAmount);
        transactions.add(transactionOne);

        LocalDateTime transactionTwoTime = LocalDateTime.of(2020, 7, 10, 10, 0);
        BigDecimal transactionTwoAmount = BigDecimal.valueOf(50L);
        BigDecimal transactionTwoBalance = transactionOneAmount.add(transactionTwoAmount);
        Transaction transactionTwo = new Transaction(
                transactionTwoTime, transactionTwoAmount, transactionTwoBalance);
        transactions.add(transactionTwo);

        assertEquals(transactions.lastBalance(), transactionTwoBalance);
    }
}
