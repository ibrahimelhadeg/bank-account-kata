package com.soge.katas.accounts.statements;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.soge.katas.accounts.transactions.Transaction;

public class Statement implements Iterable<Transaction> {

    private final List<Transaction> lines;

    public Statement(List<Transaction> lines) {
        this.lines = lines;
    }

    public boolean isEmpty() {
        return this.lines.isEmpty();
    }

    @Override
    public Iterator<Transaction> iterator() {
        return this.lines.iterator();
    }

    public Stream<Transaction> stream() {
        return this.lines.stream();
    }
}
