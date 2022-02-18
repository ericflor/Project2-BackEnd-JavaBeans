package com.revature.controllers;

import com.revature.models.Group;
import com.revature.models.User;
import com.revature.services.GroupService;
import com.revature.services.UserService;
import com.revature.utils.CookiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
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
            System.out.println(user.toString());
            Group group = user.getGroup();
            if(group!=null){
                System.out.println(group.toString());
                return ResponseEntity.status(200).body(group);
            }
            System.out.println("204====================================");
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(401).build();
    }

    @PostMapping
    public ResponseEntity<User> addGroup(@CookieValue(name = "upNext_user") String userCookie,
                                          @RequestBody Group group){
        User user = CookiesUtil.isCookieValid(userCookie);

        if(user!=null) {
            Group newGroup = groupService.saveGroup(group);
            if(newGroup!=null) {
                user.setGroup(newGroup);
                user.setRoleId(2);
                if (userService.addOrUpdateUser(user)) {
                    user = userService.getUser(user.getId());
                    ResponseCookie cookie = CookiesUtil.buildResponseCookie(user);
                    System.out.println(user.toString());
                    return ResponseEntity.status(201).header(HttpHeaders.SET_COOKIE, cookie.toString()).body(user);
                }
                return ResponseEntity.status(400).build();
            }

            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(401).build();
    }

    @PutMapping
    public ResponseEntity<User> updateGroup(@CookieValue(name = "upNext_user") String userCookie,
                                             @RequestBody Group group){
        //get user info
        User user = CookiesUtil.isCookieValid(userCookie);
        user = userService.getUser(user.getId());
        //check if user is admin of given group, otherwise return Unauthorized
        System.out.println(group.toString());
        System.out.println(user.toString());
        if(user != null && user.getRoleId() == 2 && user.getGroup().getId() == group.getId()){
            Group newGroup = groupService.saveGroup(group);
            if(newGroup!=null){
                ResponseCookie cookie = CookiesUtil.buildResponseCookie(user);
                return ResponseEntity.status(202).header(HttpHeaders.SET_COOKIE, cookie.toString()).body(user);
            }
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(401).build();
    }

    @PutMapping("/join/{id}")
    public ResponseEntity<User> joinGroup(@CookieValue(name = "upNext_user") String userCookie,
                                           @PathVariable int id){
        User user = CookiesUtil.isCookieValid(userCookie);
        if(user!=null){
            Group group = groupService.getGroupById(id);
            System.out.println(group.toString());
            if(group.isOpen()){
                user.setGroup(group);
                user.setRoleId(1);
                if(userService.addOrUpdateUser(user)){
                    System.out.println(user.toString());
                    user = userService.getUser(user.getId());
                    System.out.println(user.toString());
                    ResponseCookie cookie = CookiesUtil.buildResponseCookie(user);
                    return ResponseEntity.status(202).header(HttpHeaders.SET_COOKIE, cookie.toString()).body(user);
                }
                return ResponseEntity.status(400).build();
            }
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(401).build();
    }

    @PutMapping("/leave")
    public ResponseEntity<User> leaveGroup(@CookieValue(name = "upNext_user") String userCookie){
        User user = CookiesUtil.isCookieValid(userCookie);
        user = userService.getUser(user.getId());
        System.out.println(user.toString());
        if(user!=null){// && user.getGroup()!=null){
            if(user.getRoleId()==2){
                int groupId = user.getGroup().getId();
                user.setRoleId(0);
                userService.removeGroupFromUsers(user.getGroup().getId());
                groupService.deleteGroupById(groupId);
            }else{
                user.setGroup(null);
                user.setRoleId(0);
                userService.addOrUpdateUser(user);
            }
            user = userService.getUser(user.getId());
            ResponseCookie cookie = CookiesUtil.buildResponseCookie(user);
            return ResponseEntity.status(200).header(HttpHeaders.SET_COOKIE, cookie.toString()).body(user);
        }
        return ResponseEntity.status(401).build();

    }

}
