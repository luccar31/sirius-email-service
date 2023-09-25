package com.ldav.siriusemailservice.exception;

import org.springframework.http.HttpStatus;

public class ApiServerException extends ApiException{

    public ApiServerException() {
    }

    public ApiServerException(String message, HttpStatus statusCode) {
        super(message, statusCode);
    }

    public ApiServerException(String message, Throwable cause, HttpStatus statusCode) {
        super(message, cause, statusCode);
    }

    public ApiServerException(Throwable cause, HttpStatus statusCode) {
        super(cause, statusCode);
    }
}
