package com.revature.services;

import com.revature.models.Decisions;
import com.revature.models.User;
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
        System.out.println(decisions);
        try {
            System.out.println("youre in try block 1");
        if(decisions.getUser().getRoleId()==2) {
            Decisions decision1 = decisionDao.findByImdbIdAndRoundIdAndUserId(decisions.getImdbId(), decisions.getRoundId(), decisions.getUser().getId());
            if(decision1.getDecisionsId()>0) {
                decisions.setDecisionsId(decision1.getDecisionsId());
            }
            System.out.println(decision1);
        }
        }catch(Exception e){
            System.out.println("catch block 1 hit");
            e.printStackTrace();
            decisionDao.save(decisions);
            return true;
        }
        try {
            System.out.println("try block 2 babyyyy");
            System.out.println(decisions);

            decisionDao.save(decisions);


        }catch(Exception e){
            System.out.println("catch block 2 hit");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Decisions> getMoviesForUsers(int roundId, int groupId){
        User admin = userDAO.findByGroupIdAndRoleId(groupId, 2);
        return decisionDao.findDistinctByRoundIdAndUserId(roundId, admin.getId());
    }

    public String getRoundWinner(int groupId){
        int numUsers = userDAO.findByGroupId(groupId).size(); //# of users in this group
        int numDecisions = decisionDao.countByUserGroupId(groupId); //# of decisions group made

        if(numUsers*10 == numDecisions){
            System.out.println(decisionDao.getWinner(groupId));
            return  decisionDao.getWinner(groupId);
        }
        return "No winner yet!";
    }



    //after voting we will delete the movies from DB
    public void deleteMovies(int id){
        decisionDao.deleteById(id);
    }


}
