package com.periodical.trots.controllers.user;

import com.periodical.trots.WebSecurityConfig;
import com.periodical.trots.services.SecurityService;
import com.periodical.trots.services.UserService;
import com.periodical.trots.web.UserValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(UserController.class)
class BalanceControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @MockBean
    private UserService userService;

    @MockBean
    private SecurityService securityService;

    @MockBean
    private UserValidator userValidator;

    @Qualifier("userDetailsServiceImpl")
    @MockBean
    private UserDetailsService userDetailsService;


    @Test
    void topUpBalanceGetTest() throws Exception {
        String url = "/login";
        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(MockMvcResultMatchers.status().isOk());
    }
}