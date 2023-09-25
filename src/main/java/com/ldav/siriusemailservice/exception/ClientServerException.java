package com.ldav.siriusemailservice.exception;

import org.springframework.http.HttpStatus;

public class ClientServerException extends ApiException{

    public ClientServerException() {
    }

    public ClientServerException(String message, HttpStatus statusCode) {
        super(message, statusCode);
    }

    public ClientServerException(String message, Throwable cause, HttpStatus statusCode) {
        super(message, cause, statusCode);
    }

    public ClientServerException(Throwable cause, HttpStatus statusCode) {
        super(cause, statusCode);
    }
}
