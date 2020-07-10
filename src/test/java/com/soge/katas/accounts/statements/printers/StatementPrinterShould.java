package com.soge.katas.accounts.statements.printers;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soge.katas.accounts.statements.Statement;
import com.soge.katas.accounts.transactions.Transaction;

import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
public class StatementPrinterShould {

    @Test
    public void print_a_formatted_statement_to_the_console(@Mock PrintStream console) {

        List<Transaction> transactions = new ArrayList<>();

        LocalDateTime transactionOneTime = LocalDateTime.of(2020, 7, 9, 15, 0);
        BigDecimal transactionOneAmount = BigDecimal.valueOf(100L);
        Transaction transactionOne = new Transaction(
                Transaction.Type.DEPOSIT, transactionOneTime, transactionOneAmount, transactionOneAmount);
        transactions.add(transactionOne);

        LocalDateTime transactionTwoTime = LocalDateTime.of(2020, 7, 10, 10, 0);
        BigDecimal transactionTwoAmount = BigDecimal.valueOf(50L);
        BigDecimal transactionTwoBalance = transactionOneAmount.subtract(transactionTwoAmount);
        Transaction transactionTwo = new Transaction(
                Transaction.Type.WITHDRAWAL, transactionTwoTime, transactionTwoAmount, transactionTwoBalance);
        transactions.add(transactionTwo);

        Statement statement = new Statement(transactions);
        InOrder inOrder = inOrder(console);

        StatementPrinter statementPrinter = new ConsoleStatementPrinter(console, new LineFormatter());
        statementPrinter.print(statement);

        inOrder.verify(console).println("OPERATION | DATE | AMOUNT | BALANCE");
        inOrder.verify(console).println("WITHDRAWAL | 10/07/2020 | 50.00 | 50.00");
        inOrder.verify(console).println("DEPOSIT | 09/07/2020 | 100.00 | 100.00");
    }
}
