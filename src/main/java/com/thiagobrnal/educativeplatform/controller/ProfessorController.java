package com.thiagobrnal.educativeplatform.controller;

import com.thiagobrnal.educativeplatform.dto.ProfessorUserDTO;
import com.thiagobrnal.educativeplatform.model.Professor;
import com.thiagobrnal.educativeplatform.model.Role;
import com.thiagobrnal.educativeplatform.model.UserSec;
import com.thiagobrnal.educativeplatform.repository.IRoleRepository;
import com.thiagobrnal.educativeplatform.service.ICourseService;
import com.thiagobrnal.educativeplatform.service.IProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professor")
@PreAuthorize("denyAll()")
public class ProfessorController {

    @Autowired
    private IProfessorService professorService;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<Professor>> listProfessor(){
        List<Professor> listProf = professorService.findAll();
        return ResponseEntity.ok(listProf);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping
    public ResponseEntity<Professor> registerProfessor(@RequestBody ProfessorUserDTO professorUserDTO){
        //crear user
        UserSec userSec = new UserSec();
        userSec.setEmail(professorUserDTO.getEmail());
        userSec.setPassword(passwordEncoder.encode(professorUserDTO.getPassword()));
        userSec.setEnabled(true);
        userSec.setAccountNotLocked(true);
        userSec.setAccountNotExpired(true);
        userSec.setCredentialNotExpired(true);

        //Asignar rol al profesor
        Role role = roleRepository.findEntityByRole("PROFESOR").orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        userSec.getRoleList().add(role);

        //Crear profesor
        Professor professor = new Professor();
        professor.setNombre(professorUserDTO.getNombre());
        professor.setApellido(professorUserDTO.getApellido());
        professor.setUser(userSec);

        //Guardar
        Professor saved = professorService.save(professor);
        return ResponseEntity.ok(saved);
    }
}
