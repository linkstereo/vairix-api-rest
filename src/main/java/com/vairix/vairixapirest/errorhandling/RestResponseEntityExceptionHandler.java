package com.vairix.vairixapirest.errorhandling;

import com.vairix.vairixapirest.errorhandling.model.ErrorBody;
import com.vairix.vairixapirest.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ CharacterNotExistException.class })
    public ResponseEntity<ErrorBody> handleCharacterNotExistException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                ErrorBody.builder().mensaje("El personaje no existe").build(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ UserNotExistException.class })
    public ResponseEntity<ErrorBody> handleUserNotExistException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                ErrorBody.builder().mensaje("El usuario no existe").build(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ EmailExistException.class })
    public ResponseEntity<ErrorBody> handleEmailExistException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                ErrorBody.builder().mensaje("El correo ya esta registrado").build(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ InvalidEmailException.class })
    public ResponseEntity<ErrorBody> handleInvalidEmailException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                ErrorBody.builder().mensaje("El correo es invalido. Debe ser de la forma <name>@<domain>").build(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ BadRequestException.class })
    public ResponseEntity<ErrorBody> handleBadRequestException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                ErrorBody.builder().mensaje(ex.getCause().getMessage()).build(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<ErrorBody> handleAuthenticationException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                ErrorBody.builder().mensaje(ex.getMessage()).build(),
                new HttpHeaders(),
                HttpStatus.UNAUTHORIZED);
    }


}
