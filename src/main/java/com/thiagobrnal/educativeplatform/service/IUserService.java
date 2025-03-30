package com.thiagobrnal.educativeplatform.service;

import com.thiagobrnal.educativeplatform.model.UserSec;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    //read
    public List<UserSec> findAll();

    //create
    public UserSec save(UserSec userSec);

    //update
    public UserSec update(UserSec userSec);

    //delete
    public void deleteById(Long id);

    //findby
    public Optional<UserSec> findById(Long id);

    //encript
    public String encriptPassword(String password);
}
