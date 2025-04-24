package com.thiagobrnal.educativeplatform.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCurso;
    private String division;
    private Float caragaHoraria;

    @ManyToMany
    @JoinTable(name = "curses_estudents",joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "estudent_id"))
    private Set<Estudent> estudents = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "professor_id",nullable = false)
    private Professor professor;

    public Course(Long id, String nombreCurso, String division, Float caragaHoraria, Set<Estudent> estudents, Professor professor) {
        this.id = id;
        this.nombreCurso = nombreCurso;
        this.division = division;
        this.caragaHoraria = caragaHoraria;
        this.estudents = estudents;
        this.professor = professor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Float getCaragaHoraria() {
        return caragaHoraria;
    }

    public void setCaragaHoraria(Float caragaHoraria) {
        this.caragaHoraria = caragaHoraria;
    }

    public Set<Estudent> getEstudents() {
        return estudents;
    }

    public void setEstudents(Set<Estudent> estudents) {
        this.estudents = estudents;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
