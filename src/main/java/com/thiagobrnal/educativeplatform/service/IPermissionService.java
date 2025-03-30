package com.thiagobrnal.educativeplatform.service;

import com.thiagobrnal.educativeplatform.model.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {

    //findBy
    public Optional<Permission> findById(Long id);

    //read
    public List<Permission> findAll();

    //create
    public Permission save(Permission permission);

    //update
    public Permission update(Permission permission);

    //delete

    public void deleteById(Long id);
}
