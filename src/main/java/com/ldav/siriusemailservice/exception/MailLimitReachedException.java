package com.ldav.siriusemailservice.exception;

public class MailLimitReachedException extends MailSenderException{
    public MailLimitReachedException() {
    }

    public MailLimitReachedException(String message) {
        super(message);
    }

    public MailLimitReachedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailLimitReachedException(Throwable cause) {
        super(cause);
    }
}
