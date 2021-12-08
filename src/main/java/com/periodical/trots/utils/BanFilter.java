package com.periodical.trots.utils;

import com.periodical.trots.entities.UserEntity;
import com.periodical.trots.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class BanFilter implements Filter {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserEntity user = (UserEntity) ((HttpServletRequest) servletRequest).getSession().getAttribute("USER");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI();
        System.out.println("filter");
        if (user != null && userService.findByUsername(user.getUsername()).getBanStatus() != null) {
            ((HttpServletRequest) servletRequest).getSession().invalidate();
            System.out.println("User banned");
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}