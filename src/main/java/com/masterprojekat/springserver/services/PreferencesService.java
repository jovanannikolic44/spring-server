package com.masterprojekat.springserver.services;

import com.masterprojekat.springserver.model.Instrument;
import com.masterprojekat.springserver.model.Preferences;
import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.repository.InstrumentRepository;
import com.masterprojekat.springserver.repository.PreferencesRepository;
import com.masterprojekat.springserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PreferencesService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PreferencesRepository preferencesRepository;
    @Autowired
    private InstrumentRepository instrumentRepository;

    public void savePreferences(String username, Set<String> selectedInstrumentNames) {
        Optional<User> userOptional = userRepository.findById(username);
        if(userOptional.isEmpty())
            throw new RuntimeException("Korisnik sa korisnickim imenom " + username + " ne postoji!");
        User user = userOptional.get();
        Preferences preferences = preferencesRepository.findByUser(user).orElse(new Preferences());
        preferences.setUser(user);

        // Convert strings to Instrument
        Set<Instrument> selectedInstruments = new HashSet<>();
        for (String instrumentName : selectedInstrumentNames) {
            Optional<Instrument> instrument = instrumentRepository.findByName(instrumentName);
            if (instrument.isEmpty()) {
                throw new RuntimeException("Instrument " + instrumentName + " nije pronađen!");
            }
            selectedInstruments.add(instrument.get());
        }

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
        Set<String> instrumentNames = new HashSet<>();
        for (Instrument instrument : preferences.getSelectedInstruments()) {
            instrumentNames.add(instrument.getName());
        }
        return instrumentNames;
    }
}
