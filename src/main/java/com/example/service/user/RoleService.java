package com.example.service.user;

import com.example.model.user.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> getUserRole();
}
