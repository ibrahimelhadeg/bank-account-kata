package com.soge.katas.accounts.statements.printers;

import java.io.PrintStream;

import com.soge.katas.accounts.statements.Statement;
import com.soge.katas.accounts.transactions.Transaction;

public class ConsoleStatementPrinter implements StatementPrinter {

    private final LineFormatter lineFormatter;
    private final PrintStream console;

    public ConsoleStatementPrinter(final PrintStream console,
                                   final LineFormatter lineFormatter) {
        this.lineFormatter = lineFormatter;
        this.console = console;
    }

    @Override
    public void print(Statement statement) {
        if (statement.isEmpty()) {
            return;
        }
        printHeader();
        printLines(statement);
    }

    private void printLines(Statement statement) {
        statement.stream().forEach(this::printLine);
    }

    private void printLine(Transaction line) {
        console.println(lineFormatter.format(line));
    }

    private void printHeader() {
        console.println("OPERATION | DATE | AMOUNT | BALANCE");
    }
}
