package com.gojek.exceptions;

public class TicketNotIssuedException extends RuntimeException {

    public TicketNotIssuedException() {
    }

    public TicketNotIssuedException(String message) {
        super(message);
    }

    public TicketNotIssuedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketNotIssuedException(Throwable cause) {
        super(cause);
    }

    public TicketNotIssuedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
