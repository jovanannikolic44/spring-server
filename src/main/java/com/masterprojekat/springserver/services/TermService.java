package com.masterprojekat.springserver.services;

import com.masterprojekat.springserver.model.Course;
import com.masterprojekat.springserver.model.Term;
import com.masterprojekat.springserver.model.TermStatus;
import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.repository.CourseRepository;
import com.masterprojekat.springserver.repository.TermRepository;
import com.masterprojekat.springserver.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class TermService {
    @Autowired
    private TermRepository termRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    public String createNewTerm(Term term) {
        User professor = userRepository.findById(term.getProfessor().getUsername()).orElseThrow((() -> new EntityNotFoundException("Profesor sa korisnickim imenom " + term.getProfessor().getUsername() + " nije pronadjen u bazi!")));
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

    public String requestTerm(int termId, String studentUsername, int courseId) {
        Term requestedTerm = termRepository.findById(termId).orElseThrow((() -> new EntityNotFoundException("Termin sa identifikatorom " + termId + " nije pronadjen u bazi!")));
        User student = userRepository.findById(studentUsername).orElseThrow((() -> new EntityNotFoundException("Student sa korisnickim imenom " + studentUsername + " nije pronadjen u bazi!")));
        Course course = courseRepository.findById(courseId).orElseThrow((() -> new EntityNotFoundException("Kurs sa identifikatorom " + courseId + " nije pronadjen u bazi!")));

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
        requestedTerm.setCourse(course);
        termRepository.save(requestedTerm);
        return "Zahtev sa rezervaciju termina je uspesno poslat!";
    }

    public String acceptTerm(int termId) {
        Term term = termRepository.findById(termId).orElseThrow((() -> new EntityNotFoundException("Termin sa identifikatorom " + termId + " nije pronadjen u bazi!")));
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
        Term term = termRepository.findById(termId).orElseThrow((() -> new EntityNotFoundException("Termin sa identifikatorom " + termId + " nije pronadjen u bazi!")));
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
        User professor = userRepository.findById(professorUsername).orElseThrow((() -> new EntityNotFoundException("Profesor sa korisnickim imenom " + professorUsername + " nije pronadjen u bazi!")));
        return termRepository.findByProfessorAndStatus(professor, TermStatus.SLOBODAN);
    }

    public List<Term> getAllRequestedTermsForProfessor(String professorUsername) {
        User professor = userRepository.findById(professorUsername).orElseThrow((() -> new EntityNotFoundException("Profesor sa korisnickim imenom " + professorUsername + " nije pronadjen u bazi!")));
        return termRepository.findByProfessorAndStatus(professor, TermStatus.ZAHTEV_POSLAT);
    }

    public List<Term> getAllConfirmedTermsForProfessor(String professorUsername) {
        User professor = userRepository.findById(professorUsername).orElseThrow((() -> new EntityNotFoundException("Profesor sa korisnickim imenom " + professorUsername + " nije pronadjen u bazi!")));
        return termRepository.findByProfessorAndStatus(professor, TermStatus.PRIHVACEN);
    }

    public List<Term> getAllConfirmedTermsForStudent(String studentUsername) {
        User student = userRepository.findById(studentUsername).orElseThrow((() -> new EntityNotFoundException("Student sa korisnickim imenom " + studentUsername + " nije pronadjen u bazi!")));
        return termRepository.findByStudentAndStatus(student, TermStatus.PRIHVACEN);
    }

    public List<Term> getTermsByDate(String studentUsername, String date) throws DateTimeParseException{
        User student = userRepository.findById(studentUsername).orElseThrow((() -> new EntityNotFoundException("Student sa korisnickim imenom " + studentUsername + " nije pronadjen u bazi!")));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return termRepository.findByStudentAndDateAndStatus(student, localDate, TermStatus.PRIHVACEN);
    }
}
