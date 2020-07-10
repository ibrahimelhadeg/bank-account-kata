package com.soge.katas.accounts;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.soge.katas.accounts.statements.Statement;
import com.soge.katas.accounts.statements.StatementGenerator;
import com.soge.katas.accounts.statements.printers.ConsoleStatementPrinter;
import com.soge.katas.accounts.statements.printers.LineFormatter;
import com.soge.katas.accounts.statements.printers.StatementPrinter;
import com.soge.katas.accounts.transactions.Transaction;

public class Main {

    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        LocalDateTime transactionOneTime = LocalDateTime.of(2020, 7, 9, 15, 0);
        BigDecimal transactionOneAmount = BigDecimal.valueOf(100L);
        Transaction transactionOne = new Transaction(
                Transaction.Type.DEPOSIT, transactionOneTime, transactionOneAmount, transactionOneAmount);
        transactions.add(transactionOne);

        LocalDateTime transactionTwoTime = LocalDateTime.of(2020, 7, 10, 10, 0);
        BigDecimal transactionTwoAmount = BigDecimal.valueOf(50L);
        BigDecimal transactionTwoBalance = transactionOne.balance().subtract(transactionTwoAmount);
        Transaction transactionTwo = new Transaction(
                Transaction.Type.WITHDRAWAL, transactionTwoTime, transactionTwoAmount, transactionTwoBalance);
        transactions.add(transactionTwo);

        LocalDateTime transactionThreeTime = LocalDateTime.of(2020, 7, 11, 11, 30);
        BigDecimal transactionThreeAmount = BigDecimal.valueOf(500L);
        BigDecimal transactionThreeBalance = transactionTwo.balance().add(transactionThreeAmount);
        Transaction transactionThree = new Transaction(
                Transaction.Type.DEPOSIT, transactionThreeTime, transactionThreeAmount, transactionThreeBalance);
        transactions.add(transactionThree);

        Statement statement = new StatementGenerator().generateStatementFor(transactions);

        StatementPrinter statementPrinter = new ConsoleStatementPrinter(System.out, new LineFormatter());
        statementPrinter.print(statement);
    }
}
