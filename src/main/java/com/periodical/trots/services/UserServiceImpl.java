package com.periodical.trots.services;

import com.periodical.trots.entities.UserEntity;
import com.periodical.trots.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean save(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("customer");
        user.setBalance(BigDecimal.valueOf(0));
        userRepository.save(user);
        return true;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<UserEntity> getAll(){ return userRepository.findAll();}

    public boolean saveUserByAdmin(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setBalance(BigDecimal.valueOf(0));
        userRepository.save(user);
        return true;
    }

    public boolean banUserById(Integer userId){
        UserEntity user = userRepository.getById(userId);
        if (user.getBanStatus() == null){
            user.setBanStatus("banned");
        }else {
            user.setBanStatus(null);
        }
        userRepository.save(user);
        return true;
    }

    public boolean deleteUserById(Integer userId){
        userRepository.deleteById(userId);
        return true;
    }

    public Double topUpBalance(Double balance, Double currentBalance, Integer userId){
        UserEntity user = userRepository.getById(userId);
        Double updatedBalance = balance+currentBalance;
        user.setBalance(BigDecimal.valueOf(updatedBalance));
        userRepository.save(user);
        return updatedBalance;
    }

    public boolean updateBalanceAfterPayment(Integer userId, Double actualBalance){
        UserEntity user = userRepository.getById(userId);
        user.setBalance(BigDecimal.valueOf(actualBalance));
        userRepository.save(user);
        return true;
    }

    public UserEntity findUserById(Integer userId){
        return userRepository.getById(userId);
    }


}
