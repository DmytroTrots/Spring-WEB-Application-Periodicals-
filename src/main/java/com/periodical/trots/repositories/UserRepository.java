package com.periodical.trots.repositories;

import com.periodical.trots.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    /**
     * Find by username user entity.
     *
     * @param username the username
     * @return the user entity
     */
    UserEntity findByUsername(String username);

    /**
     * Find by email user entity.
     *
     * @param email the email
     * @return the user entity
     */
    UserEntity findByEmail(String email);

    /**
     * Find by telephone user entity.
     *
     * @param telephone the telephone
     * @return the user entity
     */
    UserEntity findByTelephone(String telephone);
}