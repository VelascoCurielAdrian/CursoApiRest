package com.coppel.CursoApiRest.Exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionUserHelper {
  
  @ExceptionHandler(value = {FoundException.class})
  public ResponseEntity<Object> handleNotFoundUserException(FoundException e) {
    ErrorResponse error = new ErrorResponse();
    error.setTimestamp(Instant.now().toEpochMilli());
    error.setStatus(404);
    error.setError("Not Found");
    error.setMessage("NOT_FOUND");
    error.setPath("/users");
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(value = {EmailAlreadyExistsException.class})
  public ResponseEntity<Object> handleEmailAlreadyExistException(EmailAlreadyExistsException e) {
    ErrorResponse error = new ErrorResponse();
    error.setTimestamp(Instant.now().toEpochMilli());
    error.setStatus(400);
    error.setError("Bad Request");
    error.setMessage("EMAIL_ALREADY_EXIST");
    error.setPath("/users");
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(value = {InvalidRoleException.class})
  public ResponseEntity<Object> handleInvalidRoleException(InvalidRoleException e) {
    ErrorResponse error = new ErrorResponse();
    error.setTimestamp(Instant.now().toEpochMilli());
    error.setStatus(400);
    error.setError("Bad Request");
    error.setMessage("INVALID_ROLE");
    error.setPath("/users");
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    
  }
}
