package com.thiagobrnal.educativeplatform.service;

import com.thiagobrnal.educativeplatform.model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    //read
    public List<Role> findAll();

    //findby
    public Optional<Role> findById(Long id);

    //create
    public Role save (Role role);

    //update
    public Role update(Role role);

    //delete
    public void deleteById(Long id);
}
