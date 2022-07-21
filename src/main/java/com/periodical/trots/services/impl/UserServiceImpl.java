package com.periodical.trots.services.impl;

import com.periodical.trots.entities.UserEntity;
import com.periodical.trots.repositories.UserRepository;
import com.periodical.trots.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * The type User service.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean save(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("customer");
        user.setBalance(BigDecimal.ZERO);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Get all list.
     *
     * @return the list
     */
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    /**
     * Save user by admin boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean saveUserByAdmin(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setBalance(BigDecimal.ZERO);
        userRepository.save(user);
        return true;
    }

    /**
     * Ban user by id boolean.
     *
     * @param userId the user id
     * @return the boolean
     */
    public boolean banUserById(Integer userId) {
        UserEntity user = userRepository.getById(userId);
        if (user.getBanStatus() == null) {
            user.setBanStatus("banned");
        } else {
            user.setBanStatus(null);
        }
        userRepository.save(user);
        return true;
    }

    /**
     * Delete user by id boolean.
     *
     * @param userId the user id
     * @return the boolean
     */
    public boolean deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
        return true;
    }

    /**
     * Top up balance double.
     *
     * @param balance        the balance
     * @param currentBalance the current balance
     * @param userId         the user id
     * @return the double
     */
    public BigDecimal topUpBalance(BigDecimal balance, BigDecimal currentBalance, Integer userId) {
        UserEntity user = userRepository.getById(userId);
        BigDecimal updatedBalance = balance.add(currentBalance);
        user.setBalance(updatedBalance);
        userRepository.save(user);
        return updatedBalance;
    }

    /**
     * Update balance after payment boolean.
     *
     * @param username      the username
     * @param actualBalance the actual balance
     * @return the boolean
     */
    public boolean updateBalanceAfterPayment(String username, BigDecimal actualBalance) {
        UserEntity user = userRepository.findByUsername(username);
        user.setBalance(actualBalance);
        userRepository.save(user);
        return true;
    }

    /**
     * Find user by id user entity.
     *
     * @param userId the user id
     * @return the user entity
     */
    public UserEntity findUserById(Integer userId) {
        return userRepository.getById(userId);
    }

    /**
     * Find user by email user entity.
     *
     * @param email the email
     * @return the user entity
     */
    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Find user by telephone user entity.
     *
     * @param telephone the telephone
     * @return the user entity
     */
    public UserEntity findUserByTelephone(String telephone) {
        return userRepository.findByTelephone(telephone);
    }


}
