package com.rest.webservices.restfulwebservices.service;

import com.rest.webservices.restfulwebservices.exceptions.NoUserAvailableException;
import com.rest.webservices.restfulwebservices.exceptions.UserAlreadyExistsException;
import com.rest.webservices.restfulwebservices.exceptions.UserNotFoundException;
import com.rest.webservices.restfulwebservices.model.User;
import com.rest.webservices.restfulwebservices.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        log.info("UserService.findAllUsers method is called....");
        if(userRepository.findAll()==null || userRepository.findAll().size()==0){
            log.error("no user found in the database");
            throw new NoUserAvailableException("There is no user in the database");
        }
        else {
            log.info("Database is not empty. there are users in the database.");
            return userRepository.findAll();
        }
    }

    public User saveUser(User user) {
        log.info("UserService.saveUser method is called....");
        if(userRepository.findById(user.getUserId()).isPresent()) {
           log.error("user with id : " +user.getUserId()+ " is already exists and cannot be added");
           throw new UserAlreadyExistsException("user already exists. cannot be added");
        }
        else {
            log.info("user with id = " +user.getUserId() +" is going to be added..");
            return userRepository.save(user);
        }
    }

    public User findUserById(int id) {
        log.info("UserService.findUserById method is called....");
        if(userRepository.findById(id).isPresent()) {
            log.info("user with id = "+id+ " exists and is retrieved");
            return userRepository.findById(id).get();
        }
        else
        {
            log.error("user with id = "+id+ " not found");
            throw new UserNotFoundException("user is not exists");
        }
    }
}
