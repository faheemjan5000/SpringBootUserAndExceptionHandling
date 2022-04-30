package com.rest.webservices.restfulwebservices.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

//apply to all controllers
@Slf4j
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class) //for now lets say it handles all exceptions
    public final ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest webRequest){
        log.error("inside handleAllExceptions..........");
     //when an exception happens we want to create our own specific expection bean.
       ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),exception.getMessage(),webRequest.getDescription(false));
    return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);//for now lets say internal server error.
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception, WebRequest webRequest){
        log.error("inside handleUserNotFoundException method..........");
        //when this exception happens we want to create our own specific expection bean.
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoUserAvailableException.class)
    public final ResponseEntity<Object> handleNoUserAvailableException(NoUserAvailableException exception, WebRequest webRequest){
        log.error("inside handleNoUserAvailableException method..........");
        //when this exception happens we want to create our own specific expection bean.
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public final ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException exception, WebRequest webRequest){
        log.error("inside handleUserAlreadyExistsException method..........");
        //when this exception happens we want to create our own specific expection bean.
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.ALREADY_REPORTED);
    }

    @Override  //method to validate the attributes of our classes.
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("inside handleMethodArgumentNotValid method..........");
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"validation failed,plz see the details",ex.getBindingResult().toString());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);

    }


}
