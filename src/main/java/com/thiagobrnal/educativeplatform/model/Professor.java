package com.thiagobrnal.educativeplatform.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    @OneToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id",unique = true)
    private UserSec user;

    @OneToMany(mappedBy = "professor",cascade = CascadeType.ALL)
    private Set<Course> courses =new HashSet<>();

    public Professor(Long id, String nombre, String apellido, UserSec user, Set<Course> courses) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.user = user;
        this.courses = courses;
    }

    public Professor() {
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

    public Set<Course> getCourses() {
        return courses;
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

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
