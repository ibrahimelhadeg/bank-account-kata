package com.soge.katas.accounts.exceptions;

public class NonSufficientFundsException extends Exception {

    private static final long serialVersionUID = 42L;

    public NonSufficientFundsException() {
        super();
    }

    public NonSufficientFundsException(String message, Throwable cause,
                                       boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NonSufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonSufficientFundsException(String message) {
        super(message);
    }

    public NonSufficientFundsException(Throwable cause) {
        super(cause);
    }
}
