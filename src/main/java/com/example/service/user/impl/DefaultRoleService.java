package com.example.service.user.impl;

import com.example.dao.user.RoleDao;
import com.example.model.user.Role;
import com.example.service.user.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DefaultRoleService implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public Role getByName(String roleName) {
        return roleDao.getByName(roleName);
    }
}
