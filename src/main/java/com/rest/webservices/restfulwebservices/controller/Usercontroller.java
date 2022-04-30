package com.rest.webservices.restfulwebservices.controller;

import com.rest.webservices.restfulwebservices.Dao.UserDaoService;
import com.rest.webservices.restfulwebservices.exceptions.NoUserAvailableException;
import com.rest.webservices.restfulwebservices.exceptions.UserNotFoundException;
import com.rest.webservices.restfulwebservices.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/users")
public class Usercontroller {

    @Autowired
    UserDaoService userService;

    @GetMapping("/")
    public List<User> getAllUsers() throws NoUserAvailableException {
        log.info("Usercontroller.getAllUsers method is called");
        return userService.findAll();
    }

    @PostMapping("/add")
    public User saveUser(@Valid @RequestBody User user){
        log.info("Usercontroller.saveUser is called...");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) throws UserNotFoundException {
        log.info("Usercontroller.getUserById is called..");
        return userService.findUserById(id);
    }
}
