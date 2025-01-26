package com.masterprojekat.springserver.controller;

import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/user/get-all")
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @PostMapping("/user/save")
    public User saveUser(@RequestBody User user) {
        return userDao.save(user);
    }

}
