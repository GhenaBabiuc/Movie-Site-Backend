package com.example.service.user;

import com.example.model.user.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    User getByUsername(String username);

    @Transactional
    void save(User user);
}
