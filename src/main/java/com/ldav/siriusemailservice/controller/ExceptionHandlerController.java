package com.ldav.siriusemailservice.controller;

import com.ldav.siriusemailservice.exception.MailLimitReachedException;
import com.ldav.siriusemailservice.exception.NotAvailableMailSenderFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex, WebRequest request){

        var body = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed");

        var violations = ex.getConstraintViolations();
        body.setProperty("errors", violations.stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ))
        );

        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Nullable
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers, @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {

        var body = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed");

        var fieldErrors = ex.getBindingResult().getFieldErrors();
        body.setProperty("errors", fieldErrors.stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage, Collectors.toList()))
                )
        );

        return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(MailLimitReachedException.class)
    public ResponseEntity<?> handleMailLimitReachedException(MailLimitReachedException ex, WebRequest request) {
        var body = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, "Daily limit for sending emails has been reached. Try tomorrow!");

        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(NotAvailableMailSenderFoundException.class)
    public ResponseEntity<?> handleNotAvailableMailSenderFoundException(NotAvailableMailSenderFoundException ex, WebRequest request){
        var body = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "No sending email provider found for this request");

        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
