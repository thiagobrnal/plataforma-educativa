package com.thiagobrnal.educativeplatform.repository;

import com.thiagobrnal.educativeplatform.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
}
