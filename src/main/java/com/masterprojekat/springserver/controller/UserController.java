package com.masterprojekat.springserver.controller;

import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/user/get-all")
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @GetMapping("/user/get-by-username")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        User user = userDao.getByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/user/check-email-and-phone-number")
    public ResponseEntity<Map<String, String>> checkEmailAndPhoneNumberUniqueness(@RequestParam String email, @RequestParam String phoneNumber) {
        boolean emailExists = userDao.checkIfEmailExists(email);
        boolean phoneExists = userDao.checkIfPhoneNumberExists(phoneNumber);
        Map<String, String> response = new HashMap<>();

        if (emailExists && phoneExists) {
            response.put("message", "Email i broj telefona su zauzeti.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        else if (emailExists) {
            response.put("message", "Email je zauzet.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        else if (phoneExists) {
            response.put("message", "Broj telefona je zauzet.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        response.put("status", "ok");
        response.put("message", "Email i broj telefona su jedinstveni.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/user/save")
    public User saveUser(@RequestBody User user) {
        return userDao.save(user);
    }

}
