package com.example.service.user.impl;

import com.example.dao.user.RoleDao;
import com.example.dao.user.UserDao;
import com.example.model.user.Role;
import com.example.model.user.User;
import com.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service
public class DefaultUserService implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getByName("ROLE_USER"));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }
}
