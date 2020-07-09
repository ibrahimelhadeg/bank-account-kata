package com.soge.katas.accounts.transactions;

import java.math.BigDecimal;

public interface Transactions {

    void add(Transaction amount);

    BigDecimal lastBalance();
}

