package com.example.service.user;

import com.example.model.user.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    void save(User user);

    @Transactional
    User getByUsername(String username);
}
