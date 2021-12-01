package com.periodical.trots.services;

import com.periodical.trots.entities.UserEntity;
import com.periodical.trots.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void saveUserTest() {
        UserEntity user = new UserEntity();
        boolean userCreated = userService.save(user);

        Assert.assertTrue(userCreated);
        Assert.assertEquals("customer", user.getRole());
        Assert.assertEquals(0.0, user.getBalance().doubleValue(), 0.01);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);

    }

    @Test
    void getAllUserTest() {
        List<UserEntity> list = userService.getAll();

        Assert.assertNotNull(list);

        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findByUsernameTest() {
        UserEntity user = new UserEntity();
        user.setUsername("customer");

        Mockito.doReturn(new UserEntity())
                .when(userRepository)
                .findByUsername("customer");

        user = userService.findByUsername(user.getUsername());

        Assert.assertNotNull(user);
    }

    @Test
    void saveUserByAdminTest(){
        UserEntity user = new UserEntity();
        boolean userCreated = userService.saveUserByAdmin(user);

        Assert.assertTrue(userCreated);
        Assert.assertEquals(0.0, user.getBalance().doubleValue(), 0.01);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void banUserTest(){
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setBanStatus(null);

        Mockito.doReturn(new UserEntity()).when(userRepository).getById(1);
        boolean isBanned = userService.banUserById(user.getId());

        Assert.assertTrue(isBanned);
    }

    @Test
    void deleteUserByIdTest(){
        UserEntity user = new UserEntity();
        user.setId(1);

        boolean deleteUser = userService.deleteUserById(user.getId());

        Assert.assertTrue(deleteUser);
    }

    @Test
    void topUpBalanceTest(){
        UserEntity user = new UserEntity();
        user.setBalance(BigDecimal.valueOf(200));
        user.setId(1);

        Mockito.doReturn(new UserEntity()).when(userRepository).getById(1);

        Double currentBalance = user.getBalance().doubleValue();

        Double balance = userService.topUpBalance(200.0,currentBalance, user.getId());

        Assert.assertEquals(400.0,balance, 0.01);
    }

    @Test
    void updateBalanceTest(){
        UserEntity user = new UserEntity();
        user.setBalance(BigDecimal.valueOf(200));
        user.setId(1);

        Mockito.doReturn(new UserEntity()).when(userRepository).getById(1);

        boolean isUpdated = userService.updateBalanceAfterPayment(user.getId(), 400.0);

        Assert.assertTrue(isUpdated);
    }

    @Test
    void findUserByIdTest(){
        UserEntity user = new UserEntity();
        user.setId(1);

        Mockito.doReturn(new UserEntity())
                .when(userRepository)
                .getById(1);

        user = userService.findUserById(user.getId());

        Assert.assertNotNull(user);
    }
}