package com.revature.controllers;


import com.revature.models.Decisions;
import com.revature.models.Favorites;
import com.revature.models.Group;
import com.revature.models.User;
import com.revature.services.DecisionService;
import com.revature.services.UserService;
import com.revature.utils.CookiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
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
    public ResponseEntity<Decisions> addMovies(@CookieValue(name = "upNext_user") String cookie,
                                               @RequestBody Decisions decisions){
        System.out.println(decisions);
        User user = CookiesUtil.isCookieValid(cookie); // get user id from session cookie
        System.out.println(user);

        if(decisionService.addMovies(decisions)){
            return ResponseEntity.status(201).build();

    }
        return ResponseEntity.status(400).build();
    }


//    add liked movies to db based on user - might need to change model from choice boolean. Right now
//    I have all liked movies being pushed into a liked array.
//    @PostMapping
//    public ResponseEntity<Decisions> addLikeByUser(@CookieValue(name = "upNext_user") String cookie,
//                                                  @RequestBody Decisions decisions){
//        User user = CookiesUtil.isCookieValid(cookie); // get user id from session cookie
//
//        if(user != null) { // making sure someone is logged in
//
//            decisions.setUser(user); // save imdb movie name by user id
//
//            if (decisionService.savLike(decisions)) {
//
//                return ResponseEntity.status(201).build();
//
//            }
//            return ResponseEntity.status(400).build();
//        }
//
//        return ResponseEntity.status(401).build();
//    }
//

    @PutMapping //If new round is started, update the ten movies
    public ResponseEntity<Decisions> newRound(@RequestBody Decisions decisions){
        if(decisionService.addMovies(decisions)){
            return ResponseEntity.status(202).build();
        }
        return ResponseEntity.status(400).build();
    }


}
