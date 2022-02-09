package com.revature.repos;

import com.revature.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public boolean save(User o) {
        return false;
    }

    @Override
    public boolean update(User o) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User get(int id) {
        return null;
    }
}
