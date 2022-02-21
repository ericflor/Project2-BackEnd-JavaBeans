package com.revature.controllers;

import com.revature.models.User;
import com.revature.services.GroupService;
import com.revature.services.UserService;
import com.revature.utils.CookiesUtil;
import com.revature.utils.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user){
        System.out.println(user);
        user.setGroup(null);
        user.setPassword(Encryption.stringToMD5(user.getPassword()));

        if(userService.addOrUpdateUser(user)){
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        System.out.println("==================HELLOOO====================");

        if(user.getPassword()==""){
            User user2 = userService.getUser(user.getId());
            user.setPassword(user2.getPassword());
        }else{
            user.setPassword(Encryption.stringToMD5(user.getPassword()));
        }

        if(userService.addOrUpdateUser(user)){
            //user.setPassword("");
            ResponseCookie cookie = CookiesUtil.buildResponseCookie(user);

            return ResponseEntity.status(201).header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@CookieValue(name = "upNext_user") String cookie, @PathVariable("id") int id){

        User user = CookiesUtil.isCookieValid(cookie);

        if(user!=null){

            User user_id = userService.getUser(id);

            return ResponseEntity.status(200).body(user_id);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUser());
    }

    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser(@CookieValue(name = "upNext_user") String cookie){
        User user = CookiesUtil.isCookieValid(cookie);
        if(user!=null){
            user = userService.getUser(user.getId());
            user.setPassword("");
            return ResponseEntity.status(200).body(user);
        }
        return ResponseEntity.status(401).build();
    }
}