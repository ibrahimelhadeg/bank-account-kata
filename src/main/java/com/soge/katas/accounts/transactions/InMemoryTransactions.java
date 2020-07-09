package com.soge.katas.accounts.transactions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InMemoryTransactions implements Transactions {

    private final List<Transaction> transactions;

    public InMemoryTransactions() {
        this.transactions = new ArrayList<>();
    }

    @Override
    public void add(final Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public BigDecimal lastBalance() {
        return transactions.get(transactions.size() - 1).balance();
    }

    public List<Transaction> transactions() {
        return transactions;
    }
}
