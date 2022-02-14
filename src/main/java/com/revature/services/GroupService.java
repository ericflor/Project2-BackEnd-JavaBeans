package com.revature.services;

import com.revature.models.Group;
import com.revature.models.User;
import com.revature.repos.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService {

    private GroupDao groupDao;

    @Autowired
    public GroupService(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public Group getGroupById(int id){
        return groupDao.findById(id).orElse(null);
    }

    public boolean saveGroup(Group group){
        try {
            groupDao.save(group);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void deleteGroupById(int id){
        groupDao.deleteById(id);
    }

}
