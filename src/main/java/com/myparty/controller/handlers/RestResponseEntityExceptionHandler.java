package com.myparty.controller.handlers;

import org.json.JSONObject;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.myparty.exception.UserException;
import com.myparty.exception.AuthException;
import com.myparty.exception.NoValueFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
    extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class,
      javax.validation.ConstraintViolationException.class })
  protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "This should be application specific";
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = { PSQLException.class })
  protected ResponseEntity<Object> conflict(RuntimeException ex, WebRequest request) {
    JSONObject json = new JSONObject();
    json.put("error", ex.getMessage());
    return handleExceptionInternal(ex, json.toString(), new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = { UserException.class, AuthException.BadCredentialException.class })
  protected ResponseEntity<Object> unauthorized(RuntimeException ex, WebRequest request) {
    JSONObject json = new JSONObject();
    json.put("error", ex.getMessage());
    return handleExceptionInternal(ex, json.toString(), new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
  }

  @ExceptionHandler(value = { NoValueFoundException.class })
  protected ResponseEntity<Object> notFound(RuntimeException ex, WebRequest request) {
    JSONObject json = new JSONObject();
    json.put("error", ex.getMessage());
    return handleExceptionInternal(ex, json.toString(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

}