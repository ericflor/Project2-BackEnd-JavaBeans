package com.revature.services;

import com.revature.models.User;
import com.revature.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDAO userDAO;

    public UserService() {}

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    User getUserById(int id) {
        // Checks if user ID is valid
        if (id > 0) {
            return this.userDAO.get(id);
        }
        return null;
    }
}
