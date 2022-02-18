package com.revature.services;

import com.revature.models.User;
import com.revature.repos.FavoriteDao;

import com.revature.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDAO userDao;
    private FavoriteDao favoriteDao;

    @Autowired
    public UserService(UserDAO userDao) {
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

    public List<User> getAllUser(){
        return userDao.findAll();
    }

    public List<User> getUsersByGroupId(int groupId){
        return userDao.findByGroupId(groupId);
    }
    public void removeGroupFromUsers(int groupId){
        try {
            List<User> groupMembers = userDao.findByGroupId(groupId);
            for (User member : groupMembers) {
                member.setGroup(null);
                userDao.save(member);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
