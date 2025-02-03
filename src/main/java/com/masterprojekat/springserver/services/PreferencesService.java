package com.masterprojekat.springserver.services;

import com.masterprojekat.springserver.model.Preferences;
import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.repository.PreferencesRepository;
import com.masterprojekat.springserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PreferencesService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PreferencesRepository preferencesRepository;

    public void savePreferences(String username, Set<String> selectedInstruments) {
        Optional<User> userOptional = userRepository.findById(username);
        if(userOptional.isEmpty())
            throw new RuntimeException("Korisnik sa korisnickim imenom " + username + " ne postoji!");
        User user = userOptional.get();
        Preferences preferences = preferencesRepository.findByUser(user).orElse(new Preferences());
        preferences.setUser(user);
        preferences.setSelectedInstruments(selectedInstruments);
        preferencesRepository.save(preferences);

        // update user first login
        user.setFirstLogIn(false);
        userRepository.save(user);
    }

    public Set<String> getPreferences(String username) {
        Optional<User> userOptional = userRepository.findById(username);
        if(userOptional.isEmpty())
            throw new RuntimeException("Korisnik sa korisnickim imenom " + username + " ne postoji!");
        User user = userOptional.get();
        Preferences preferences = preferencesRepository.findByUser(user).orElse(new Preferences());
        return preferences.getSelectedInstruments();
    }
}
