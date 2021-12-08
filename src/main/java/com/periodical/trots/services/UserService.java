package com.periodical.trots.services;

import com.periodical.trots.entities.UserEntity;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Save boolean.
     *
     * @param user the user
     * @return the boolean
     */
    boolean save(UserEntity user);

    /**
     * Find by username user entity.
     *
     * @param username the username
     * @return the user entity
     */
    UserEntity findByUsername(String username);
}
