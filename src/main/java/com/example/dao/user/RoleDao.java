package com.example.dao.user;

import com.example.model.user.Role;

public interface RoleDao {

    Role getByName(String roleName);
}
