package com.soge.katas.accounts.statements;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soge.katas.accounts.transactions.Transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StatementGeneratorShould {

    @Test
    public void create_a_statement_with_statement_lines_in_reverse_transactions_order() {

        LocalDateTime transactionOneTime = LocalDateTime.of(2020, 7, 9, 15, 0);
        BigDecimal transactionOneAmount = BigDecimal.valueOf(100L);
        Transaction transactionOne = new Transaction(
                Transaction.Type.DEPOSIT, transactionOneTime, transactionOneAmount, transactionOneAmount);

        LocalDateTime transactionTwoTime = LocalDateTime.of(2020, 7, 10, 10, 0);
        BigDecimal transactionTwoAmount = BigDecimal.valueOf(50L);
        BigDecimal transactionTwoBalance = transactionOneAmount.add(transactionTwoAmount);
        Transaction transactionTwo = new Transaction(
                Transaction.Type.DEPOSIT, transactionTwoTime, transactionTwoAmount, transactionTwoBalance);

        List<Transaction> transactionsList = List.of(transactionOne, transactionTwo);

        StatementGenerator generator = new StatementGenerator();
        Statement statement = generator.generateStatementFor(transactionsList);

        Iterator<Transaction> lineIterator = statement.iterator();
        assertEquals(lineIterator.next().time(), transactionTwoTime);
        assertEquals(lineIterator.next().time(), transactionOneTime);
    }
}
