package com.masterprojekat.springserver.controller;

import com.masterprojekat.springserver.model.Course;
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

    @PutMapping("/user/update-info")
    public User updateUserInfo(@RequestBody User newUser) {
        return userService.updateInfo(newUser);
    }

    @PutMapping("/user/update-password")
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

    @PostMapping("/user/purchase-course")
    public ResponseEntity<String> purchaseCourse(@RequestParam String username, @RequestParam int courseId) {
        userService.purchaseCourse(username, courseId);
        return ResponseEntity.ok("Kurs je uspesno kupljen!");
    }

    @GetMapping("/user/get-purchased-courses")
    public ResponseEntity<List<Course>> getPurchasedCourses(@RequestParam String username) {
        List<Course> purchasedCourses = userService.getPurchasedCourses(username);
        if(purchasedCourses != null) {
            return ResponseEntity.ok(purchasedCourses );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/user/add-to-cart-course")
    public ResponseEntity<String> addCourseToCart(@RequestParam String username, @RequestParam int courseId) {
        userService.addCourseToCart(username, courseId);
        return ResponseEntity.ok("Kurs je uspesno dodat u korpu!");
    }

    @GetMapping("/user/get-courses-from-cart")
    public ResponseEntity<List<Course>> getCoursesFromCart(@RequestParam String username) {
        List<Course> coursesFromCart = userService.getCoursesFromCart(username);
        if(coursesFromCart != null) {
            return ResponseEntity.ok(coursesFromCart );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/users/{username}/cart")
    public ResponseEntity<?> removeCoursesFromCart(@PathVariable String username, @RequestBody List<Integer> courseIds) {
        userService.removeCoursesFromCart(username, courseIds);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/is-course-purchased")
    public ResponseEntity<Boolean> isCoursePurchased(@RequestParam String username, @RequestParam int courseId) {
        boolean isPurchased = userService.isCoursePurchased(username, courseId);
        return ResponseEntity.ok(isPurchased);
    }

    @GetMapping("/user/user-account-requests")
    public ResponseEntity<List<User>> getUserAccountRequests() {
        List<User> userAccountRequests = userService.getUserAccountRequests();
        if(userAccountRequests != null) {
            return ResponseEntity.ok(userAccountRequests );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/user/accept-request")
    public ResponseEntity<String> acceptRequest(@RequestParam String username) {
        String message = "";
        message = userService.acceptRequest(username);
        if(!"Nalog je aktiviran!".equals(message)) {
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok(message);
    }

    @PostMapping("/user/decline-request")
    public ResponseEntity<String> declineRequest(@RequestParam String username) {
        String message = "";
        message = userService.declineRequest(username);
        if(!"Aktivacija naloga je odbijena!".equals(message)) {
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok(message);
    }
}
