package com.masterprojekat.springserver.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int termId;

    private LocalDate date;
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "professor_username")
    private User professor;

    @ManyToOne
    @JoinColumn(name = "student_username")
    private User student;

    @Enumerated(EnumType.STRING)
    private TermStatus status;

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public User getProfessor() {
        return professor;
    }

    public void setProfessor(User professor) {
        this.professor = professor;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public TermStatus getStatus() {
        return status;
    }

    public void setStatus(TermStatus status) {
        this.status = status;
    }
}
