package com.example.service.user;

import com.example.model.user.User;
import com.example.model.user.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User createNewUser(UserRegistrationDto userRegistrationDto);
}
