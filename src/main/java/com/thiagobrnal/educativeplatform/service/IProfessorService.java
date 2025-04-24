package com.thiagobrnal.educativeplatform.service;

import com.thiagobrnal.educativeplatform.model.Professor;

import java.util.List;
import java.util.Optional;

public interface IProfessorService {

    //read
    public List<Professor> findAll();

    //findById
    public Optional<Professor> findById(Long id);

    //create
    public Professor save(Professor professor);

    //update
    public Professor updateProfessor(Professor professor);

    //delete
    public void deleteById(Long id);

    //create professor with user
    //public Professor createProfessor(String nombre, String apellido, String email, String password);
}
