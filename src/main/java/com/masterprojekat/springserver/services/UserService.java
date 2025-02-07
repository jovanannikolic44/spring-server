package com.masterprojekat.springserver.services;

import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Streamable.of(userRepository.findAll()).forEach(users::add);
        return users;
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User getByUsername(String username) {
        return userRepository.findById(username).orElse(null);
    }

    public boolean checkIfEmailExists(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    public boolean checkIfPhoneNumberExists(String phoneNumber) {
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        return user.isPresent();
    }

    public void updateProfilePicture(String username, String imagePath) {
        User user = userRepository.findById(username).orElseThrow(() -> new RuntimeException("Ne postoji korisnik sa korisnickim imenom " + username + "!"));
        user.setProfilePicture(imagePath);
        userRepository.save(user);
    }
}
