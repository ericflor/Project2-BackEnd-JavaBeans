package com.revature.services;

import com.revature.models.Group;
import com.revature.repos.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    public Group saveGroup(Group group){
        try {
            group = groupDao.save(group);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return group;
    }

    public void deleteGroupById(int id){
        groupDao.deleteById(id);
    }

}
