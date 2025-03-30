package com.thiagobrnal.educativeplatform.controller;

import com.thiagobrnal.educativeplatform.model.Permission;
import com.thiagobrnal.educativeplatform.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permission")
@PreAuthorize("hasRole('ADMINISTRADOR')")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping
    public ResponseEntity<List<Permission>> listPermission(){
        List<Permission> permissionList = permissionService.findAll();
        return ResponseEntity.ok(permissionList);
    }

    @PostMapping
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission){
        Permission newPermission = permissionService.save(permission);
        return ResponseEntity.ok(newPermission);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permission> findPermissionById(@PathVariable Long id){
        Optional<Permission> permission = permissionService.findById(id);
        return permission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Permission> editPermission(@RequestBody Permission permission, @PathVariable Long id){
        Permission newPermission = permissionService.findById(id).orElse(null);
        if (newPermission != null){
            newPermission = permission;
        }
            permissionService.update(newPermission);
            return ResponseEntity.ok(newPermission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePermission(@PathVariable Long id){
        permissionService.deleteById(id);
        return ResponseEntity.ok("El permiso se elimino con exito");
    }
}
