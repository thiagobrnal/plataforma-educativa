package com.thiagobrnal.educativeplatform.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_permissions", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissionList = new HashSet<>();

    public Role(Long id, String role, Set<Permission> permissionList) {
        this.id = id;
        this.role = role;
        this.permissionList = permissionList;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public Set<Permission> getPermissionList() {
        return permissionList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPermissionList(Set<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
