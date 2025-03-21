package com.masterprojekat.springserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Preferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int preferencesId;

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, unique = true)
    @JsonIgnore
    private User user;

    @ManyToMany
    @JoinTable(
            name = "user_instruments",
            joinColumns = @JoinColumn(name = "preferencesId"),
            inverseJoinColumns = @JoinColumn(name = "instrument_id")
    )
    private Set<Instrument> selectedInstruments;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Instrument> getSelectedInstruments() {
        return selectedInstruments;
    }

    public void setSelectedInstruments(Set<Instrument> selectedInstruments) {
        this.selectedInstruments = selectedInstruments;
    }

    public int getPreferencesId() {
        return preferencesId;
    }

    public void setPreferencesId(int preferencesId) {
        this.preferencesId = preferencesId;
    }
}
