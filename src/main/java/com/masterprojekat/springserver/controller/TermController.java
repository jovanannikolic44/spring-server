package com.masterprojekat.springserver.controller;

import com.masterprojekat.springserver.model.Term;
import com.masterprojekat.springserver.model.TermStatus;
import com.masterprojekat.springserver.services.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

@RestController
public class TermController {
    @Autowired
    private TermService termService;

    @PostMapping("/term/create-new")
    public ResponseEntity<String> createNewTerm(@RequestBody Term term) {
        String message = "";
        message = termService.createNewTerm(term);
        if(!"Termin je uspesno dodat!".equals(message)) {
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok(message);
    }

    @PostMapping("/term/reserve")
    public ResponseEntity<String> requestTerm(@RequestParam int termId, @RequestParam String studentUsername, @RequestParam int courseId) {
        String message = "";
        message = termService.requestTerm(termId, studentUsername, courseId);
        if(!"Zahtev sa rezervaciju termina je uspesno poslat!".equals(message)) {
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok(message);
    }

    @PostMapping("/term/accept")
    public ResponseEntity<String> acceptTerm(@RequestParam int termId) {
        String message = "";
        message = termService.acceptTerm(termId);
        if(!"Termin prihvacen!".equals(message)) {
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok(message);
    }

    @PostMapping("/term/decline")
    public ResponseEntity<String> rejectTerm(@RequestParam int termId) {
        String message = "";
        message = termService.rejectTerm(termId);
        if(!"Termin odbijen!".equals(message)) {
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok(message);
    }

    @GetMapping("/term/get-available-terms-for-professor")
    public ResponseEntity<List<Term>> getAllAvailableTermsForProfessor(@RequestParam String professorUsername) {
        List<Term> allAvailableTerms = termService.getAllAvailableTermsForProfessor(professorUsername);
        return ResponseEntity.ok(allAvailableTerms);
    }

    @GetMapping("/term/get-requested-terms-for-professor")
    public ResponseEntity<List<Term>> getAllRequestedTermsForProfessor(@RequestParam String professorUsername) {
        List<Term> allRequestedTerms = termService.getAllRequestedTermsForProfessor(professorUsername);
        return ResponseEntity.ok(allRequestedTerms);
    }

    @GetMapping("/term/get-confirmed-terms-for-professor")
    public ResponseEntity<List<Term>> getAllConfirmedTermsForProfessor(@RequestParam String professorUsername) {
        List<Term> allConfirmedTerms = termService.getAllConfirmedTermsForProfessor(professorUsername);
        return ResponseEntity.ok(allConfirmedTerms);
    }

    @GetMapping("/term/get-confirmed-terms-for-student")
    public ResponseEntity<List<Term>> getAllConfirmedTermsForStudent(@RequestParam String studentUsername) {
        List<Term> allConfirmedTerms = termService.getAllConfirmedTermsForStudent(studentUsername);
        return ResponseEntity.ok(allConfirmedTerms);
    }

    @GetMapping("/term/get-terms-by-date")
    public ResponseEntity<?> getTermsByDate(@RequestParam String username, @RequestParam String type, @RequestParam String inputDate, @RequestParam TermStatus termStatus) {
        try {
            List<Term> termsList = termService.getTermsByDate(username, type, inputDate, termStatus);
            return ResponseEntity.ok(termsList);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @GetMapping("/term/get-channel-name")
    public ResponseEntity<String> getChannelName(@RequestParam int termId) {
        String channelName = termService.getChannelName(termId);
        return ResponseEntity.ok(channelName);
    }
}
