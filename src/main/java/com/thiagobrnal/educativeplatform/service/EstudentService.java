package com.thiagobrnal.educativeplatform.service;

import com.thiagobrnal.educativeplatform.model.Estudent;
import com.thiagobrnal.educativeplatform.repository.IEstudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudentService implements IEstudentService{

    @Autowired
    private IEstudentRepository estudentRepository;

    @Override
    public List<Estudent> findAll() {
        return estudentRepository.findAll();
    }

    @Override
    public Optional<Estudent> findById(Long id) {
        return estudentRepository.findById(id);
    }

    @Override
    public Estudent save(Estudent estudent) {
        return estudentRepository.save(estudent);
    }

    @Override
    public Estudent updateEstudent(Estudent estudent) {
        return estudentRepository.save(estudent);
    }

    @Override
    public void deleteById(Long id) {
        estudentRepository.deleteById(id);
    }
}
