package com.periodical.trots.services;

import com.periodical.trots.entities.UserEntity;

public interface UserService {
    void save(UserEntity user);
    UserEntity findByUsername(String username);
}
