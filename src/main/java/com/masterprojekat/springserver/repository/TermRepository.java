package com.masterprojekat.springserver.repository;

import com.masterprojekat.springserver.model.Term;
import com.masterprojekat.springserver.model.TermStatus;
import com.masterprojekat.springserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TermRepository extends JpaRepository<Term, Integer> {
    int countByProfessorAndStatus(User professor, TermStatus status);
    boolean existsByProfessorAndDateAndTimeAndStatusIn(User professor, LocalDate date, LocalTime time, List<TermStatus> statuses);
    List<Term> findByProfessorAndStatus(User professor, TermStatus status);
    List<Term> findByProfessorAndStatusAndDateGreaterThanEqual(User professor, TermStatus status, LocalDate date);
    List<Term> findByStudentAndStatus(User student, TermStatus status);
    List<Term> findByStudentAndDateAndStatus(User student, LocalDate date, TermStatus status);
    List<Term> findByProfessorAndDateAndStatus(User professor, LocalDate date, TermStatus status);
    Term findByChannel(String channel);

    @Modifying
    @Query("UPDATE Term t SET t.status = 'NIJE_ODRZAN' WHERE (t.status = 'SLOBODAN' OR t.status = 'ZAHTEV_POSLAT' " +
            "or t.status = 'PRIHVACEN') AND t.date < :now")
    void updateMissedTerms(@Param("now") LocalDate now);
}
