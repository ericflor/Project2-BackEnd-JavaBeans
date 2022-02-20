package com.revature.controllers;

import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.services.LoginService;
import com.revature.utils.CookiesUtil;
import com.revature.utils.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200", "https://34.224.173.86:7000"}, allowCredentials = "true")
@RestController
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity loginRequest(@RequestBody UserDTO user) {
        // Encrypts Password as soon as received
        user.password = Encryption.stringToMD5(user.password);
        // Checks if account information is valid
        User u = loginService.validateAccount(user.username, user.password);
        if (u != null){
            u.setPassword("");
            ResponseCookie cookie = CookiesUtil.buildResponseCookie(u);
            System.out.println(u.toString());
            return ResponseEntity.status(200).header(HttpHeaders.SET_COOKIE, cookie.toString()). body(u);
        }
        return ResponseEntity.status(400).build();
    }

    @PostMapping("/logout")
    public ResponseEntity logoutRequest() {
        // Grabs an Empty Cookie that expires immediately
        ResponseCookie empty_cookie = CookiesUtil.nullResponseCookie();
        // Returns 200 with Empty Cookie
        return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.SET_COOKIE, empty_cookie.toString()).build();
    }

    // Added this method just to test the login also to show how it should work
    @GetMapping("/testlogin")
    public ResponseEntity testValidation(@CookieValue(name = "upNext_user") String user){
        /* @CookieValue annotation in any controller method you want to have user verification
            the cookie's name is upNext_user we can change the name if needed.
         */
        User validatedUser = CookiesUtil.isCookieValid(user);
        // If User is validated
        if (validatedUser != null) {
            // If user is valid we return 200 Status
            // We can also check user role by doing validatedUser.role
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }
}
