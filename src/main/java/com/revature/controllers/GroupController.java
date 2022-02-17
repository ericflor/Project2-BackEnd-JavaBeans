package com.revature.controllers;

import com.revature.models.Group;
import com.revature.models.User;
import com.revature.services.GroupService;
import com.revature.services.UserService;
import com.revature.utils.CookiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@CrossOrigin
@RestController
@RequestMapping("/group")
public class GroupController {

    private GroupService groupService;
    private UserService userService;

    @Autowired
    public GroupController(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Group> getGroup(@CookieValue(name = "upNext_user") String userCookie){
        User user = CookiesUtil.isCookieValid(userCookie);
        if(user!=null){
            Group group = user.getGroup();
            if(group!=null){
                return ResponseEntity.status(200).body(group);
            }
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(401).build();
    }

    @PostMapping
    public ResponseEntity<Group> addGroup(//@CookieValue(name = "upNext_user") String userCookie,
                                          @RequestBody Group group){
        //User user = CookiesUtil.isCookieValid(userCookie);
        //if(user!=null) {
            Group newGroup = groupService.saveGroup(group);
            if(newGroup!=null){
                //user.setGroup(newGroup);
                //user.setRoleId(2);
                //if(userService.addOrUpdateUser(user)){
                    return ResponseEntity.status(201).body(newGroup);
                }
                //return ResponseEntity.status(400).build();

            return ResponseEntity.status(400).build();
        //}
        //return ResponseEntity.status(401).build();
    }

    @PutMapping
    public ResponseEntity<Group> updateGroup(@CookieValue(name = "upNext_user") String userCookie,
                                             @RequestBody Group group){
        //get user info
        User user = CookiesUtil.isCookieValid(userCookie);
        //check if user is admin of given group, otherwise return Unauthorized
        if(user != null && user.getRoleId() == 2 && user.getGroup().getId() == group.getId()){
            Group newGroup = groupService.saveGroup(group);
            if(newGroup!=null){
                return ResponseEntity.status(202).body(newGroup);
            }
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(401).build();
    }

    @PutMapping("/join/{id}")
    public ResponseEntity<Group> joinGroup(@PathVariable int id){
        Group group = groupService.getGroupById(id);
        if(group.isOpen()){
            //get user from session?
            //user.setGroup(group)
            //if(userService.addOrUpdateUser(user)){}
            return ResponseEntity.status(202).build();
        }
        //get user from session?
        //user.setGroup(group)
        //if(userService.addOrUpdateUser(user)){}
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/leave")
    public ResponseEntity<Group> leaveGroup(){
        //get user from session?
        //Group group =
        //check group is not null
        Group group = new Group();
        //user.setGroup(null)
        //if(user.getRole == "ADMIN"){
        groupService.deleteGroupById(group.getId());
        //if(userService.addOrUpdateUser(user)){}
        return ResponseEntity.status(200).build();
    }

}
