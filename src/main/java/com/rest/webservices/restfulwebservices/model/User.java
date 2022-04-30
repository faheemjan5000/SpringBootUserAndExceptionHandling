package com.rest.webservices.restfulwebservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer userId;

    @Size(min = 2 , message = "Name should be atleast two characters")
    private String userName;

    @Past(message = "birthdate should be in the past")
    private Date userBirthDate;
}
