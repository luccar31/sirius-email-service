package com.ldav.siriusemailservice.exception;

public class NotAvailableMailSenderFoundException extends MailSenderException {
    public NotAvailableMailSenderFoundException() {
    }

    public NotAvailableMailSenderFoundException(String message) {
        super(message);
    }

    public NotAvailableMailSenderFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAvailableMailSenderFoundException(Throwable cause) {
        super(cause);
    }

    protected NotAvailableMailSenderFoundException(String message, Throwable cause, boolean enableSuppression,
                                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
