package com.soge.katas.accounts.statements.printers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soge.katas.accounts.transactions.Transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LineFormatterShould {

    @Test
    public void format_a_statement_line() {

        LineFormatter lineFormatter = new LineFormatter();

        LocalDateTime transactionTime = LocalDateTime.of(2020, 7, 10, 15, 0);
        BigDecimal transactionAmount = BigDecimal.valueOf(100L);
        Transaction transaction = new Transaction(
                Transaction.Type.DEPOSIT, transactionTime, transactionAmount, transactionAmount);

        assertEquals("DEPOSIT | 10/07/2020 | 100.00 | 100.00", lineFormatter.format(transaction));
    }
}
