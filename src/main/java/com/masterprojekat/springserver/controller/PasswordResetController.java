package com.masterprojekat.springserver.controller;

import com.masterprojekat.springserver.services.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class PasswordResetController {
    @Autowired
    private PasswordResetService passwordResetService;
    private static final Logger logger = LoggerFactory.getLogger(PasswordResetController.class);

    @PostMapping("/reset-service/request-reset")
    public void requestPasswordReset(@RequestParam String toEmail) {
        System.out.println("Sending mail");
        logger.debug("Sending mail");
        passwordResetService.sendPasswordResetEmail(toEmail);
    }

    @PostMapping("/reset-service/update-password")
    public ResponseEntity<String> updatePassword(@RequestParam String token, @RequestParam String newPassword) {
        passwordResetService.updatePassword(token, newPassword);
        return ResponseEntity.ok("Lozinka je uspesno azurirana!");
    }

}
