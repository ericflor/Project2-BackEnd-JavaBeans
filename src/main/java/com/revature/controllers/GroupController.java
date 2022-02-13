package com.revature.controllers;

import com.revature.models.Group;
import com.revature.models.User;
import com.revature.services.GroupService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Group> getGroup(){
        //get user from session?
        User user = new User();
        Group group = groupService.getGroupById(user.getId());
        if(group!=null){
            return ResponseEntity.status(200).body(group);
        }
        return ResponseEntity.status(204).build();
    }

    @PostMapping
    public ResponseEntity<Group> addGroup(@RequestBody Group group){
        if(groupService.saveGroup(group)){
            //get user from session?
            //user.setGroup(group);
            //user.setRoleId(2);``
            //if(userService.addOrUpdateUser(user)){}
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping
    public ResponseEntity<Group> updateGroup(@RequestBody Group group){
        if(groupService.saveGroup(group)){
            return ResponseEntity.status(202).build();
        }
        return ResponseEntity.status(400).build();
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
