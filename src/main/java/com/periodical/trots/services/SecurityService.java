package com.periodical.trots.services;

/**
 * The interface Security service.
 */
public interface SecurityService {
    /**
     * Is authenticated boolean.
     *
     * @return the boolean
     */
    boolean isAuthenticated();

    /**
     * Auto login.
     *
     * @param username the username
     * @param password the password
     */
    void autoLogin(String username, String password);
}
