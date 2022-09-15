/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.controllers;

import com.sg.guessthenumber.models.data.service.FinishedGameException;
import com.sg.guessthenumber.models.data.service.InvalidGameIDException;
import com.sg.guessthenumber.models.data.service.InvalidGuessException;
import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Teresa
 */
@ControllerAdvice
@RestController
public class GameControllerExceptionHandler extends ResponseEntityExceptionHandler {
    
    private static final String MESSAGE = "ERROR: Please try again.";
    private static final String MESSAGE2 = "ERROR: Please try again and enter a guess that consists of exactly 4 valid numbers.";

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<Error> handleSqlException(
            SQLIntegrityConstraintViolationException ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage(MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler(FinishedGameException.class)
    public final ResponseEntity<Error> handleFinishedGameException(
            FinishedGameException ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage(ex.getLocalizedMessage());
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler(InvalidGameIDException.class)
    public final ResponseEntity<Error> handleInvalidGameIDException(
            InvalidGameIDException ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage(ex.getLocalizedMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(InvalidGuessException.class)
    public final ResponseEntity<Error> handleInvalidGuessException(
            InvalidGuessException ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage(ex.getLocalizedMessage());
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public final ResponseEntity<Error> handleArrayIndexOutOfBoundsException(
            ArrayIndexOutOfBoundsException ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage(MESSAGE2);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
