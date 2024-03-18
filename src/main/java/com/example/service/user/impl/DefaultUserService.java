package com.example.service.user.impl;

import com.example.dao.user.UserDao;
import com.example.model.user.User;
import com.example.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class DefaultUserService implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }
}
