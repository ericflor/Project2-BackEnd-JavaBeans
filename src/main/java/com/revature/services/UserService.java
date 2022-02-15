package com.revature.services;

import com.revature.models.User;
import com.revature.repos.FavoriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserDao userDao;
    private FavoriteDao favoriteDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(int id){
        Optional<User> user = userDao.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        return new User();
    }

    public boolean addOrUpdateUser(User user){
        try{
            userDao.save(user);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
