package com.thiagobrnal.educativeplatform.repository;

import com.thiagobrnal.educativeplatform.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfessorRepository extends JpaRepository<Professor,Long> {
}
