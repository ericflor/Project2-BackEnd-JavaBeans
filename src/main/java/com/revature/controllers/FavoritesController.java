package com.revature.controllers;


import com.revature.models.Favorites;
import com.revature.models.Group;
import com.revature.models.User;
import com.revature.services.FavoriteService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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

//    @GetMapping
//    public ResponseEntity<Favorites> getFavsByUser(){
//        User user = new User();
//        Favorites favorites = (Favorites) favoriteService.getFavs(user.getId());
//        if(favorites!=null){
//            return ResponseEntity.status(200).body(favorites);
//        }
//        return ResponseEntity.status(204).build();
//    }

    @PostMapping
    public ResponseEntity<Favorites> addFavByUser(@RequestBody Favorites favorites){
        if(favoriteService.saveFav(favorites)){
            //User user = new User();
            // get user_id from session & save imdb_id by their id
            return ResponseEntity.status(201).build();

        }
        return ResponseEntity.status(400).build();
    }

    // testing - to be deleted later
    @GetMapping
    public ResponseEntity<List<Favorites>> getAllFavs(){
        return ResponseEntity.status(200).body(favoriteService.getAllFavorites());
    }
}
