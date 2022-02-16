package com.revature.services;


import com.revature.repos.DecisionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DecisionService {

    private DecisionDao decisionDao;

    @Autowired
    public DecisionService(DecisionDao decisionDao){
        this.decisionDao = decisionDao;
    }

    public List<>

}
