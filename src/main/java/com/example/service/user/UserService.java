package com.example.service.user;

import com.example.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void save(User user);

    void activateUser(String username);
}
