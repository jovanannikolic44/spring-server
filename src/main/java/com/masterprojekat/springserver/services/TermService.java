package com.masterprojekat.springserver.services;

import com.masterprojekat.springserver.model.Term;
import com.masterprojekat.springserver.model.TermStatus;
import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.repository.TermRepository;
import com.masterprojekat.springserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermService {
    @Autowired
    private TermRepository termRepository;
    @Autowired
    private UserRepository userRepository;

    public String createNewTerm(Term term) {
        User professor = userRepository.findById(term.getProfessor().getUsername()).orElseThrow();
        if (!"Profesor".equalsIgnoreCase(professor.getType())) {
            return "Korisnik nije profesor!";
        }
        int availableClasses = termRepository.countByProfessorAndStatus(professor, TermStatus.SLOBODAN);
        if(availableClasses >= 10) {
            return "Moguce je kreirati 10 slobodnih termina!";
        }
        boolean termWithSameProfessorDateAndTimeExists = termRepository.existsByProfessorAndDateAndTimeAndStatusIn(
                professor,
                term.getDate(),
                term.getTime(),
                List.of(TermStatus.SLOBODAN, TermStatus.ZAHTEV_POSLAT, TermStatus.PRIHVACEN));
        if(termWithSameProfessorDateAndTimeExists) {
            return "Termin vec postoji ili je zauzet!";
        }
        term.setProfessor(professor);
        term.setStatus(TermStatus.SLOBODAN);
        term.setStudent(null);
        termRepository.save(term);
        return "Termin je uspesno dodat!";
    }

    public String requestTerm(int termId, String studentUsername) {
        Term requestedTerm = termRepository.findById(termId).orElseThrow();
        User student = userRepository.findById(studentUsername).orElseThrow();
        if (!"Ucenik".equalsIgnoreCase(student.getType())) {
            return "Korisnik nije student!";
        }
        boolean termAlreadyReserved = termRepository.existsByProfessorAndDateAndTimeAndStatusIn(
                requestedTerm.getProfessor(), requestedTerm.getDate(), requestedTerm.getTime(),
                List.of(TermStatus.ZAHTEV_POSLAT, TermStatus.PRIHVACEN)
        );
        if(termAlreadyReserved) {
            return "Termin je zauzet u prosledjeno vreme kod prosledjenog profesora!";
        }
        requestedTerm.setStatus(TermStatus.ZAHTEV_POSLAT);
        requestedTerm.setStudent(student);
        termRepository.save(requestedTerm);
        return "Zahtev sa rezervaciju termina je uspesnp poslat!";
    }

    public String acceptTerm(int termId) {
        Term term = termRepository.findById(termId).orElseThrow();
        if(term.getStatus() != TermStatus.ZAHTEV_POSLAT) {
            return "Status termina nije validan!";
        }
        boolean isTermIsAlreadyConfirmed = termRepository.existsByProfessorAndDateAndTimeAndStatusIn(
                term.getProfessor(), term.getDate(), term.getTime(),
                List.of(TermStatus.PRIHVACEN)
        );
        if(isTermIsAlreadyConfirmed) {
            return "Termin je vec prihvacen!";
        }
        term.setStatus(TermStatus.PRIHVACEN);
        termRepository.save(term);
        return "Termin prihvacen!";
    }

    public String rejectTerm(int termId) {
        Term term = termRepository.findById(termId).orElseThrow();
        if(term.getStatus() != TermStatus.ZAHTEV_POSLAT) {
            return "Status termina nije validan!";
        }
        boolean isTermIsAlreadyRejected = termRepository.existsByProfessorAndDateAndTimeAndStatusIn(
                term.getProfessor(), term.getDate(), term.getTime(),
                List.of(TermStatus.ODBIJEN)
        );
        if(isTermIsAlreadyRejected) {
            return "Termin je vec odbijen!";
        }
        term.setStatus(TermStatus.ODBIJEN);
        termRepository.save(term);
        return "Termin odbijen!";
    }

    public List<Term> getAllAvailableTermsForProfessor(String professorUsername) {
        User professor = userRepository.findById(professorUsername).orElseThrow();
        return termRepository.findByProfessorAndStatus(professor, TermStatus.SLOBODAN);
    }

    public List<Term> getAllRequestedTermsForProfessor(String professorUsername) {
        User professor = userRepository.findById(professorUsername).orElseThrow();
        return termRepository.findByProfessorAndStatus(professor, TermStatus.ZAHTEV_POSLAT);
    }

    public List<Term> getAllConfirmedTermsForProfessor(String professorUsername) {
        User professor = userRepository.findById(professorUsername).orElseThrow();
        return termRepository.findByProfessorAndStatus(professor, TermStatus.PRIHVACEN);
    }

    public List<Term> getAllConfirmedTermsForStudent(String studentUsername) {
        User student = userRepository.findById(studentUsername).orElseThrow();
        return termRepository.findByStudentAndStatus(student, TermStatus.PRIHVACEN);
    }
}
