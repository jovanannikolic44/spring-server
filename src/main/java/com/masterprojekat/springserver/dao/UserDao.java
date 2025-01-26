package com.masterprojekat.springserver.dao;

import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Iterable in Java -- investigate
@Service
public class UserDao {
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
}
