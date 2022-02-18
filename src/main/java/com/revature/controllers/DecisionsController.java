package com.revature.controllers;


import com.revature.models.Decisions;
import com.revature.models.Favorites;
import com.revature.models.Group;
import com.revature.models.User;
import com.revature.services.DecisionService;
import com.revature.services.UserService;
import com.revature.utils.CookiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true"
)
        @RestController
@RequestMapping("/decisions")
public class DecisionsController {

    private DecisionService decisionService;
    private UserService userService;

    @Autowired
    public DecisionsController(DecisionService decisionService, UserService userService) {
        this.decisionService = decisionService;
        this.userService = userService;
    }

    @PostMapping //add the movies to DB
    public ResponseEntity<Decisions> addMoviesByUser(@CookieValue(name = "upNext_user") String cookie,
                                                  @RequestBody Decisions decisions){
        User user = CookiesUtil.isCookieValid(cookie); // get user id from session cookie

        if(user != null) { // making sure someone is logged in

            decisions.setUser(user); // save imdb movie name by user id

            if (decisionService.addMovies(decisions)) {

                return ResponseEntity.status(201).build();

            }
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.status(401).build();
    }



    @PutMapping //If new round is started, update the ten movies
    public ResponseEntity<Decisions> newRound(@RequestBody Decisions decisions){
        if(decisionService.addMovies(decisions)){
            return ResponseEntity.status(202).build();
        }
        return ResponseEntity.status(400).build();
    }

//    @GetMapping
//    public ResponseEntity<List<Decisions>> getAllDecisions(){
//        return ResponseEntity.status(200).body(decisionService.getAllDecisions());
//    }
//
//    @GetMapping
//    public ResponseEntity<Decisions>getWinner(){
//        return ResponseEntity.status(200).body(decisionService.getWinner());
//    }


}
