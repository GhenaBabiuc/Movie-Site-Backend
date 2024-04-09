package com.example.service.user.impl;

import com.example.model.user.Role;
import com.example.repository.user.RoleRepository;
import com.example.service.user.RoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> getUserRole() {
        return roleRepository.findByName("ROLE_USER");
    }
}
