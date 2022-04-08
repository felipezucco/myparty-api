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
  protected ResponseEntity<Object> sqlException(RuntimeException ex, WebRequest request) {
    JSONObject json = new JSONObject();
    json.put("error", ex.getMessage());
    return handleExceptionInternal(ex, json.toString(), new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = { UserException.class })
  protected ResponseEntity<Object> saveAccountExceptionHandler(RuntimeException ex, WebRequest request) {
    JSONObject json = new JSONObject();
    json.put("error", ex.getMessage());
    return handleExceptionInternal(ex, json.toString(), new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
  }

  @ExceptionHandler(value = { AuthException.BadCredentialException.class })
  protected ResponseEntity<Object> authExceptionHandler(RuntimeException ex, WebRequest request) {
    JSONObject json = new JSONObject();
    json.put("error", ex.getMessage());
    return handleExceptionInternal(ex, json.toString(), new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
  }

}