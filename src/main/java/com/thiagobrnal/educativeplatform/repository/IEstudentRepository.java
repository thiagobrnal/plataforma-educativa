package com.thiagobrnal.educativeplatform.repository;

import com.thiagobrnal.educativeplatform.model.Estudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstudentRepository extends JpaRepository<Estudent, Long> {
}
