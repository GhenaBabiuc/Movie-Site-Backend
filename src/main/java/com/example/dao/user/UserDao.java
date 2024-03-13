package com.example.dao.user;

import com.example.model.user.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao {

    User getByUsername(String username);

    @Transactional
    void save(User user);
}
