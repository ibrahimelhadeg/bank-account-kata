package com.soge.katas.accounts.statements.printers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.soge.katas.accounts.transactions.Transaction;

public class LineFormatter {

    private static final String DATE_FORMATTER_PATTERN = "dd/MM/yyyy";
    private static final String DECIMAL_FORMATTER_PATTERN = "0.00";
    private static final String FIELD_SEPARATOR = " | ";

    public String format(final Transaction line) {
        return line.type() +
               FIELD_SEPARATOR +
               formatDate(line.time()) +
               FIELD_SEPARATOR +
               formatAmount(line.amount()) +
               FIELD_SEPARATOR +
               formatAmount(line.balance());
    }

    private String formatAmount(final BigDecimal amount) {
        return new DecimalFormat(DECIMAL_FORMATTER_PATTERN).format(amount);
    }

    private String formatDate(final LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern(DATE_FORMATTER_PATTERN));
    }
}
