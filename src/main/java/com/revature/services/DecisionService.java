package com.revature.services;

import com.revature.models.Decisions;
import com.revature.repos.DecisionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DecisionService {

    private DecisionDao decisionDao;

//    public DecisionService(){
//
//    }

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

    public List getWinner(List<Decisions> decisions) {
        for (int i = 0; i <= 10; i++) {
            decisionDao.countByTitleAndChoiceAndUserGroupIdAndRound
                    (decisions.getTitle(), decisions.isChoice(), decisions.getUser().getGroup().getId(), decisions.getRoundId());
            {
            return
//        return decisionDao.countByTitleAndChoiceAndUserGroupIdAndRound(decisions.getTitle(), decisions.isChoice(), decisions.getUser().getGroup().getId(), decisions.getRoundId());    }
            }
        }
    }

//        return decisionDao.countByTitleAndChoiceAndUserGroupIdAndRound(decisions.getTitle(), decisions.isChoice(), decisions.getUser().getGroup().getId(), decisions.getRoundId());    }

public List<Decisions>getAllDecisions(){
                    return getWinner(decisionDao.findAll());
            };
    //after voting we will delete the movies from DB
    public void deleteMovies(int id){
        decisionDao.deleteById(id);
    }

}
