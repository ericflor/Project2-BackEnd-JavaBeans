package com.revature.controllers;

import com.revature.models.Favorites;
import com.revature.models.User;
import com.revature.services.FavoriteService;
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
@RequestMapping("/favorites")
public class FavoritesController {

    private FavoriteService favoriteService;
    private UserService userService;

    @Autowired
    public FavoritesController(FavoriteService favoriteService, UserService userService){
        this.favoriteService = favoriteService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> addFavByUser(@CookieValue(name = "upNext_user") String cookie,
                                                  @RequestBody Favorites favorites){
        //System.out.println(favorites);
        User user = CookiesUtil.isCookieValid(cookie); // get user id from session cookie
        System.out.println(user);

        if(user != null) { // making sure someone is logged in

            favorites.setUser(user); // save imdb movie name by user id

            if (favoriteService.saveFav(favorites)) {
                user = userService.getUser(user.getId());
                ResponseCookie response_cookie = CookiesUtil.buildResponseCookie(user);
                System.out.println(user.getFavs());
                return ResponseEntity.status(201).header(HttpHeaders.SET_COOKIE, response_cookie.toString()).body(user);

            }
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.status(401).build();
    }

    @PostMapping("/delete")
    public ResponseEntity<User> deleteFavsByUser(@CookieValue(name = "upNext_user") String cookie){
        User user = CookiesUtil.isCookieValid(cookie); // get user id from session cookie
        user = userService.getUser(user.getId());
        if(user != null) { // making sure someone is logged in

            if (favoriteService.deleteFavs()) {

                ResponseCookie response_cookie = CookiesUtil.buildResponseCookie(user);
                return ResponseEntity.status(201).header(HttpHeaders.SET_COOKIE, response_cookie.toString()).body(user);

            }
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.status(401).build();
    }



    // testing - to be deleted later
    @GetMapping
    public ResponseEntity<List<Favorites>> getAllFavs(){
        return ResponseEntity.status(200).body(favoriteService.getAllFavorites());
    }
}
