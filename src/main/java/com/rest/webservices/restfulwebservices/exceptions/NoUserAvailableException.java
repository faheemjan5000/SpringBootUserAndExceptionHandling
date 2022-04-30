package com.rest.webservices.restfulwebservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoUserAvailableException extends RuntimeException{

    public NoUserAvailableException(String message) {
        super(message);
    }
}
