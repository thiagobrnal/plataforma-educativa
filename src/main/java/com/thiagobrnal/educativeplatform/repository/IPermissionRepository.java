package com.thiagobrnal.educativeplatform.repository;

import com.thiagobrnal.educativeplatform.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Long> {
}
