package com.masterprojekat.springserver.repository;

import com.masterprojekat.springserver.model.Preferences;
import com.masterprojekat.springserver.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PreferencesRepository extends CrudRepository<Preferences, String> {
    Optional<Preferences> findByUser(User user);
}
