package com.thiagobrnal.educativeplatform.service;

import com.thiagobrnal.educativeplatform.model.Estudent;

import java.util.List;
import java.util.Optional;

public interface IEstudentService {

    //read
    public List<Estudent> findAll();

    //findById
    public Optional<Estudent> findById(Long id);

    //create
    public Estudent save(Estudent estudent);

    //update
    public Estudent updateEstudent(Estudent estudent);

    //delete
    public void deleteById(Long id);
}
