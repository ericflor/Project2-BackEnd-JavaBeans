package com.revature.services;

import com.revature.models.Decisions;
import com.revature.repos.DecisionDao;
import com.revature.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DecisionService {

    private DecisionDao decisionDao;
    private UserDAO userDAO;

    @Autowired
    public DecisionService(DecisionDao decisionDao, UserDAO userDAO) {
        this.userDAO = userDAO;
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
        return decisionDao.findDistinctByRoundIdAndUserGroupId(roundId, groupId);
    }

    public String getRoundWinner(int roundId, int groupId){
        int numUsers = userDAO.findByGroupId(groupId).size(); //# of users in this group
        int numDecisions = decisionDao.countByUserGroupId(groupId); //# of decisions group made

        if(numUsers*10 == numDecisions){
            return  decisionDao.getWinner(roundId, groupId);
        }
        return "No winner yet!";
    }


    //after voting we will delete the movies from DB
    public void deleteMovies(int id){
        decisionDao.deleteById(id);
    }


}
