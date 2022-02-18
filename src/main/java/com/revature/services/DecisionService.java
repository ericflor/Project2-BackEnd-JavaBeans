package com.revature.services;

import com.revature.models.Decisions;
import com.revature.models.Group;
import com.revature.repos.DecisionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DecisionService {

    private DecisionDao decisionDao;

    @Autowired
    public DecisionService(DecisionDao decisionDao) {
        this.decisionDao = decisionDao;
    }

    //we will add movies into Decisions table
    public boolean addMovies(Decisions decisions){
        try {
            decisionDao.save(decisions);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Decisions> getMoviesForUsers(int roundId, int groupId){
        return decisionDao.findByRoundIdAndUserGroupId(roundId, groupId);
    }


    //after voting we will delete the movies from DB
    public void deleteMovies(int id){
        decisionDao.deleteById(id);
    }

}
