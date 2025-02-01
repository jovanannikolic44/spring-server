package com.masterprojekat.springserver.controller;

import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/get-all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/get-by-username")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        User user = userService.getByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/user/check-email-and-phone-number")
    public ResponseEntity<String> checkEmailAndPhoneNumberUniqueness(@RequestParam String email, @RequestParam String phoneNumber) {
        boolean emailExists = userService.checkIfEmailExists(email);
        boolean phoneExists = userService.checkIfPhoneNumberExists(phoneNumber);

        if (emailExists && phoneExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email i broj telefona su zauzeti.");
        } else if (emailExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email je zauzet.");
        } else if (phoneExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Broj telefona je zauzet.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Email i broj telefona su jedinstveni.");
        }
    }

    @PostMapping("/user/save")
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }

}
