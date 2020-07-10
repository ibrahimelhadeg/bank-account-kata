package com.soge.katas.accounts.statements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.soge.katas.accounts.transactions.Transaction;

public class StatementGenerator {

    public Statement generateStatementFor(List<Transaction> transactions) {
        return new Statement(reverseTransactionsOrder(transactions));
    }

    private List<Transaction> reverseTransactionsOrder(
            List<Transaction> statementLinesInTransactionsOrder) {
        final ArrayList<Transaction> reversedList = new ArrayList<>(statementLinesInTransactionsOrder);
        Collections.reverse(reversedList);
        return reversedList;
    }
}
