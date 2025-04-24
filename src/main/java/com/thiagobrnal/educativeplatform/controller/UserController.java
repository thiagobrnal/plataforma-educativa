package com.thiagobrnal.educativeplatform.controller;


import com.thiagobrnal.educativeplatform.model.Role;
import com.thiagobrnal.educativeplatform.model.UserSec;
import com.thiagobrnal.educativeplatform.service.IRoleService;
import com.thiagobrnal.educativeplatform.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ADMINISTRADOR')")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public ResponseEntity<List<UserSec>> getAllUsers(){
        List<UserSec> userSecList = userService.findAll();
        return ResponseEntity.ok(userSecList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSec> findUserById(@PathVariable Long id){
        Optional<UserSec> newUser = userService.findById(id);
        return newUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<UserSec> createUser(@RequestBody UserSec userSec){
        Set<Role> roleSet = new HashSet<Role>();
        Role readRole;

        userSec.setPassword(userService.encriptPassword(userSec.getPassword()));

        for (Role r: userSec.getRoleList()) {
            readRole = roleService.findById(r.getId()).orElse(null);
            if (readRole != null){
                roleSet.add(readRole);
            }
        }

        if (!roleSet.isEmpty()) {
            userSec.setRoleList(roleSet);
            UserSec newUser = userService.save(userSec);
            return ResponseEntity.ok(newUser);
        }
        return null;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserSec> editUser(@PathVariable Long id, @RequestBody UserSec userSec){
        UserSec existUser = userService.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (userSec.getEmail() != null){
            existUser.setEmail(userSec.getEmail());
        }

       if (userSec.getPassword() != null){
           existUser.setPassword(userSec.getPassword());
       }
        if (userSec.isEnabled() != null){
            existUser.setEnabled(userSec.isEnabled());
        }
        if (userSec.isAccountNotExpired() != null){
            existUser.setAccountNotExpired(userSec.isAccountNotExpired());
        }
        if (userSec.isAccountNotLocked() != null){
            existUser.setAccountNotLocked(userSec.isAccountNotLocked());
        }
        if (userSec.isCredentialNotExpired() != null){
            existUser.setCredentialNotExpired(userSec.isCredentialNotExpired());
        }

        if (userSec.getRoleList() != null){
            Set<Role> newRole = new HashSet<>();
            for (Role r:userSec.getRoleList()) {
                roleService.findById(r.getId()).ifPresent(newRole::add);
            }
            existUser.setRoleList(newRole);
        }

        UserSec newUser = userService.update(existUser);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok("El usuario fue eliminado exitosamente");
    }
}
