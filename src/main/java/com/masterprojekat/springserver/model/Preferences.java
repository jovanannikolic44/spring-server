package com.masterprojekat.springserver.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Preferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int preferencesId;

    // Referencing user
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, unique = true)
    private User user;

    @ElementCollection
    @CollectionTable(name="user_instruments", joinColumns = @JoinColumn(name = "preferencesId"))
    @Column(name = "instrument_name")
    private Set<String> selectedInstruments = new HashSet<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<String> getSelectedInstruments() {
        return selectedInstruments;
    }

    public void setSelectedInstruments(Set<String> selectedInstruments) {
        this.selectedInstruments = selectedInstruments;
    }

    public int getPreferencesId() {
        return preferencesId;
    }

    public void setPreferencesId(int preferencesId) {
        this.preferencesId = preferencesId;
    }
}
