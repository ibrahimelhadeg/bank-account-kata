package com.soge.katas.accounts.statements.printers;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soge.katas.accounts.statements.Statement;
import com.soge.katas.accounts.transactions.Transaction;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConsoleStatementPrinterShould {

    private PrintStream console;
    private LineFormatter lineFormatter;
    private StatementPrinter statementPrinter;

    @BeforeEach
    public void setUp(@Mock PrintStream console, @Mock LineFormatter lineFormatter) {
        this.console = console;
        this.lineFormatter = lineFormatter;
        statementPrinter = new ConsoleStatementPrinter(console, lineFormatter);
    }

    @Test
    public void format_statement_lines() {

        LocalDateTime transactionTime = LocalDateTime.of(2020, 7, 10, 15, 0);
        BigDecimal transactionAmount = BigDecimal.valueOf(100L);
        Transaction transaction = new Transaction(
                Transaction.Type.DEPOSIT, transactionTime, transactionAmount, transactionAmount);

        statementPrinter.print(new Statement(List.of(transaction)));

        verify(lineFormatter).format(transaction);
    }

    @Test
    public void print_a_formatted_line_on_the_console() {
        Transaction transaction = null;
        String formattedTransaction = "#####";
        when(lineFormatter.format(transaction)).thenReturn(formattedTransaction);

        statementPrinter.print(new Statement(Collections.singletonList(transaction)));

        verify(console).println("OPERATION | DATE | AMOUNT | BALANCE");
        verify(console).println(formattedTransaction);
    }

    @Test
    public void neither_format_nor_print_on_the_console_when_there_are_no_statement_lines() {
        statementPrinter.print(new Statement(List.of()));

        verify(lineFormatter, never()).format(any(Transaction.class));
        verify(console, never()).println(anyString());
        verify(console, never()).println(anyString());
    }
}
