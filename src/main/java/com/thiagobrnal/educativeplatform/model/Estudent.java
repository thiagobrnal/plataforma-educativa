package com.thiagobrnal.educativeplatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Estudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",unique = true)
    private UserSec user;

    public Estudent(Long id, String nombre, String apellido, UserSec user) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.user = user;
    }

    public Estudent() {
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public UserSec getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setUser(UserSec user) {
        this.user = user;
    }
}
