package com.ldav.siriusemailservice.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends MailSenderException{

    protected HttpStatus statusCode;

    public ApiException() {
    }

    public ApiException(String message, HttpStatus statusCode) {
        super(message);
    }

    public ApiException(String message, Throwable cause, HttpStatus statusCode) {
        super(message, cause);
    }

    public ApiException(Throwable cause, HttpStatus statusCode) {
        super(cause);
    }

}
