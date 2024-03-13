package com.example.service.user;

public interface SecurityService {

    String getLoggedInUsername();

    void autoLogin(String username, String password);
}
