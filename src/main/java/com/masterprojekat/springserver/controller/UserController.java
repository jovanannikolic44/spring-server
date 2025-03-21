package com.masterprojekat.springserver.controller;

import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.services.UserService;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    private String uploadsPath = "C:\\Users\\Jovana\\Desktop\\Master\\Projects\\spring-server\\uploads\\";

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

    @PostMapping("/user/update-info")
    public User updateUserInfo(@RequestBody User newUser) {
        return userService.updateInfo(newUser);
    }

    @PostMapping("/user/update-password")
    public User updateUserPassword(@RequestParam String username, @RequestParam String newPassword) {
        return userService.updatePassword(username, newPassword);
    }

    @PostMapping("/user/upload-profile-picture")
    public ResponseEntity<String> uploadProfilePicture(@RequestParam MultipartFile file, @RequestParam String username) {
        String profilePictureUploadPath = uploadsPath + "profile_pictures";
        File uploadDir = new File(profilePictureUploadPath);
        if (!uploadDir.exists()) {
            if (!uploadDir.mkdirs()) {
                System.err.println("Greska! Neuspesno kreiranje direktorijuma!");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greska! Neuspesno kreiranje direktorijuma!");
            }
        }

        String fileName = username + "_" + file.getOriginalFilename();
        File destination = new File(uploadDir, fileName);

        try {
            file.transferTo(destination);
            String filePath = "/uploads/profile_pictures/" + fileName;
            userService.updateProfilePicture(username, filePath);
            return ResponseEntity.status(HttpStatus.OK).body("Fajl je uspesno sacuvan na serveru!");
        } catch (IOException e) {
            System.err.println("Error in upload profile picture controller: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in file upload.");
        }
    }

    @GetMapping("/user/get-profile-picture")
    public ResponseEntity<Resource> getProfilePicture(@RequestParam String username) {
        String profilePictureUploadPath = uploadsPath + "profile_pictures";
        Path filePath = Paths.get(profilePictureUploadPath).resolve(username + "_profile_picture.jpg");
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists() && resource.isReadable())
                return ResponseEntity.status(HttpStatus.OK).body(resource);
            else
                return ResponseEntity.notFound().build();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
