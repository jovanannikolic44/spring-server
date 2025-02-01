package com.masterprojekat.springserver.services;

import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    public void sendPasswordResetEmail(String toEmail) {
        Optional<User> userOptional = userRepository.findByEmail(toEmail);
        if(userOptional.isEmpty()) {
            throw new RuntimeException("Korisnik sa email adresom " + toEmail + " ne postoji!");
        }
        // generate token and save it for current user
        User user = userOptional.get();
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        userRepository.save(user);

        String resetLink = "myapp://resetpassword?token=" + token; // Custom URL scheme for the Android app
        String subject = "Resetovanje lozinke";
        String body = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"></head><body><p>Kliknite na sledeći link kako biste resetovali lozinku: <a href=\"" + resetLink + "\">Resetujte lozinku</a></p></body></html>";

        sendEmail(toEmail, subject, body);
    }

    private void sendEmail(String toEmail, String subject, String body) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom("jovana.nikolic452@gmail.com");
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(body, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePassword(String token, String newPassword) {
        Optional<User> userOptional = userRepository.findByResetToken(token);
        if(userOptional.isEmpty()) {
            throw new RuntimeException("Neispravan ili istekao token!");
        }
        User user = userOptional.get();
        user.setPassword(newPassword);
        user.setResetToken(null);
        userRepository.save(user);
    }
}
