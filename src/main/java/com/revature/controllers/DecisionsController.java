package com.revature.controllers;


import com.revature.models.Decisions;
import com.revature.models.Group;
import com.revature.services.DecisionService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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
    public ResponseEntity<Decisions> addMovies(@RequestBody Decisions decisions){
        if(decisionService.addMovies(decisions)){
            return ResponseEntity.status(201).build();

    }
        return ResponseEntity.status(400).build();
    }

    @PutMapping //If new round is started, update the ten movies
    public ResponseEntity<Decisions> newRound(@RequestBody Decisions decisions){
        if(decisionService.addMovies(decisions)){
            return ResponseEntity.status(202).build();
        }
        return ResponseEntity.status(400).build();
    }


}
