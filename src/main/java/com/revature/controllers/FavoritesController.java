package com.revature.controllers;

import com.revature.models.Favorites;
import com.revature.models.User;
import com.revature.services.FavoriteService;
import com.revature.utils.CookiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    private FavoriteService favoriteService;

    @Autowired
    public FavoritesController(FavoriteService favoriteService){
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public ResponseEntity<Favorites> addFavByUser(@CookieValue(name = "upNext_user") String cookie,
                                                  @RequestBody Favorites favorites){
        User user = CookiesUtil.isCookieValid(cookie); // get user id from session cookie

        if(user != null) { // making sure someone is logged in

            favorites.setUser(user); // save imdb movie name by user id

            if (favoriteService.saveFav(favorites)) {

                return ResponseEntity.status(201).build();

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
