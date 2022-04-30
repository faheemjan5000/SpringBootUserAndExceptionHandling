package com.rest.webservices.restfulwebservices.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {  //whenever a exception occurs we will send response back in this specific format.

    private Date timestamp;
    private String message;
    private String details;
}
