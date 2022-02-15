package com.revature.services;

import com.revature.models.User;
import com.revature.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private UserDAO userDAO;

    @Autowired
    public LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User validateAccount(String username, String password) {

        // Checks if any of the fields are empty
        if (username.isEmpty() || password.isEmpty()){
            return null;
        }

        // Checks if account exists
        User u = userDAO.findByUsername(username);

        // User doesn't exist
        if (u == null) {
            return null;
        }
        // Checks if passwords match
        if (!u.getPassword().equals(password)){
            return null;
        }
        return u;
    }

}
