package com.masterprojekat.springserver.controller;

import com.masterprojekat.springserver.services.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class PreferencesController {
    @Autowired
    private PreferencesService preferencesService;

    @PostMapping("/preferences/save-preferences")
    public ResponseEntity<String> savePreferences(@RequestParam String username, @RequestBody Set<String> instruments) {
        preferencesService.savePreferences(username, instruments);
        return ResponseEntity.ok("Preferencije uspesno sacuvane!");
    }

    @GetMapping("/preferences/get-preferences")
    public ResponseEntity<Set<String>> getPreferences(@RequestParam String username) {
        Set<String> instruments = preferencesService.getPreferences(username);
        return ResponseEntity.ok(instruments);
    }


}
