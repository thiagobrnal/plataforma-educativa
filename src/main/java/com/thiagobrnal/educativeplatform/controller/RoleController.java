package com.thiagobrnal.educativeplatform.controller;

import com.thiagobrnal.educativeplatform.model.Permission;
import com.thiagobrnal.educativeplatform.model.Role;
import com.thiagobrnal.educativeplatform.service.IPermissionService;
import com.thiagobrnal.educativeplatform.service.IRoleService;
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
@RequestMapping("/api/role")
@PreAuthorize("hasRole('ADMINISTRADOR')")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRole(){
        List<Role> roleList = roleService.findAll();
        return ResponseEntity.ok(roleList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findRoleById(@PathVariable Long id){
        Optional<Role> newRole = roleService.findById(id);
        return newRole.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        Set<Permission> permissionSet = new HashSet<Permission>();
        Permission readPermission;

        for (Permission p: role.getPermissionList()) {
            readPermission = permissionService.findById(p.getId()).orElse(null);
            if (readPermission != null){
                permissionSet.add(readPermission);
            }
        }
        role.setPermissionList(permissionSet);
        Role newRole = roleService.save(role);
        return ResponseEntity.ok(newRole);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Role> editRole(@PathVariable Long id, @RequestBody Role role){
        Role existRole = roleService.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado"));

        if (role.getRole() != null){
            existRole.setRole(role.getRole());
        }
        if (role.getPermissionList() != null){
            Set<Permission> updatePermission = new HashSet<>();
            for (Permission p: role.getPermissionList()) {
                permissionService.findById(p.getId()).ifPresent(updatePermission::add);
            }
            existRole.setPermissionList(updatePermission);
        }

        Role newRole = roleService.update(existRole);
        return ResponseEntity.ok(newRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id){
        roleService.deleteById(id);
        return ResponseEntity.ok("role eliminado correctamente");
    }
}
