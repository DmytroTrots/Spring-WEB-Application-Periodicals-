package com.periodical.trots.utils;

import com.periodical.trots.controllers.user.ShopController;
import com.periodical.trots.entities.UserEntity;
import com.periodical.trots.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent evt) {
        String currentUserName = evt.getAuthentication().getName();
        UserEntity user = userService.findByUsername(currentUserName);
        request.getSession().setAttribute("ROLE", user.getRole());
        request.getSession().setAttribute("ID", user.getId());
        request.getSession().setAttribute("USER", user);
        logger.info("User -> " + evt.getAuthentication().getName() + ", role -> " + user.getRole());
    }
}