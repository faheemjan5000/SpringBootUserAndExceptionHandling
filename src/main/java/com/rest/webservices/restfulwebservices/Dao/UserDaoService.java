package com.rest.webservices.restfulwebservices.Dao;

import com.rest.webservices.restfulwebservices.exceptions.NoUserAvailableException;
import com.rest.webservices.restfulwebservices.exceptions.UserAlreadyExistsException;
import com.rest.webservices.restfulwebservices.exceptions.UserNotFoundException;
import com.rest.webservices.restfulwebservices.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int userCount = 3;
    static{

        users.add(new User(1,"faheem",new Date()));
        users.add(new User(2,"luca",new Date()));
        users.add(new User(3,"monica",new Date()));
        log.info("database is  loaded {} ",users);
    }

    public List<User> findAll() throws NoUserAvailableException {
        log.info("UserDaoService.findAll method called....");
        if(users==null){
            log.error("the database is empty.");
            throw new NoUserAvailableException("Currently there is no user in the database");
        }
        return users;
    }
    public User saveUser(User user) {
        log.info("UserDaoService.saveUser method called...");
        if(user.getUserId()==null){
            user.setUserId(userCount++);
        }
        if(!isUserExists(user.getUserId())) {
            log.info("user {} " +user + "is not exists so i am going to add it");
            users.add(user);
            log.info("user is added {}", user);
            return user;
        }
        else{
            throw new UserAlreadyExistsException("user with id " + user.getUserId() + " already exists");
        }

    }
    public User findUserById(int id) throws UserNotFoundException {
        log.info("UserDaoService.findUserById method is called...");
        if(!isUserExists(id)){
            log.error("user with id "+id + " not found");
            throw new UserNotFoundException("user with id " +id+ " not found");
        }
        else {
            User userFound = users.stream()
                    .filter(user -> user.getUserId().equals(id))
                    .findFirst()
                    .get();
            log.info("user with id "+ id +" found ");
            log.info("user found is : {}",userFound);
            return userFound;
        }

    }

    public boolean isUserExists(int userId){
        log.info("UserDaoService.isUserAlreadyExists() is called.....");
        return users.stream()
                .filter(user->user.getUserId().equals(userId))
                .findFirst()
                .isPresent();
    }
}
