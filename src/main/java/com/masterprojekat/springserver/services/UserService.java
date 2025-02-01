package com.masterprojekat.springserver.services;

import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Iterable in Java -- investigate
@Service
public class UserService {
    // Dependency injection mechanism -- investigate
    @Autowired
    private UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    // Guava can be used instead
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(users::add);
        return users;
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public User getByUsername(String username) {
        return repository.findById(username).orElse(null);
    }

    public boolean checkIfEmailExists(String email) {
        Optional<User> user = repository.findByEmail(email);
        return user.isPresent();
    }

    public boolean checkIfPhoneNumberExists(String phoneNumber) {
        Optional<User> user = repository.findByPhoneNumber(phoneNumber);
        return user.isPresent();
    }
}
